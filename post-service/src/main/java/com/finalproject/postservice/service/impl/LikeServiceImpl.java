package com.finalproject.postservice.service.impl;

import com.finalproject.postservice.VO.LikeOutput;
import com.finalproject.postservice.VO.User;
import com.finalproject.postservice.model.Like;
import com.finalproject.postservice.model.Post;
import com.finalproject.postservice.repository.LikeRepository;
import com.finalproject.postservice.repository.PostRepository;
import com.finalproject.postservice.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    @Autowired
    private RestTemplate restTemplate;

    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    private static final String USER_PATH = "http://localhost:8080/users/user-id-for-post/";

    @Override
    public LikeOutput createLike(Like req){
        User user = restTemplate.getForObject(USER_PATH + req.getUserId(), User.class);
        Post post = postRepository.findById(req.getPostId()).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found");
        });

        if(likeRepository.findById(req.getLikeId()).isPresent()){
           return null;
       }

        if(user.isEnabled() && user != null){
            post.setLikeCount(post.getLikeCount() + 1);
            postRepository.save(post);
        }
        LikeOutput likeOutput = new LikeOutput();
        likeOutput.setLikeId(req.getLikeId());
        likeOutput.setUser(user);
        likeOutput.setPost(post);
        likeRepository.save(req);
       return likeOutput;


    }

    @Override
    public Like getLike(String id) {
        Like like = likeRepository.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Like not found");
        });
        return like;
    }

    @Override
    public List<Like> getAllLikes() {
        List<Like> likes = likeRepository.findAll();
        return likes;
    }

    @Override
    public LikeOutput getLikeAndUser(String id) {
        Like like = this.getLike(id);
        User user = restTemplate.getForObject(USER_PATH + like.getUserId(), User.class);
        Post post = postRepository.findById(like.getPostId()).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found");
        });
        LikeOutput likeOutput = new LikeOutput();
        likeOutput.setLikeId(like.getLikeId());
        likeOutput.setUser(user);
        likeOutput.setPost(post);
        return likeOutput;
    }

    @Override
    public Like deleteLike(String id) {
        Like like = this.getLike(id);
        Post post = postRepository.findById(like.getPostId()).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found");
        });
        likeRepository.deleteById(id);
        return like;
    }
}
