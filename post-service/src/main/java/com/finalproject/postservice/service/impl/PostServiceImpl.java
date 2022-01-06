package com.finalproject.postservice.service.impl;

import com.finalproject.postservice.VO.*;
//import com.finalproject.postservice.dto.PostResponse;
//import com.finalproject.postservice.exception.PostNotFoundException;
//import com.finalproject.postservice.mapper.PostMapper;
import com.finalproject.postservice.model.Category;
import com.finalproject.postservice.model.Comment;
import com.finalproject.postservice.model.Post;
import com.finalproject.postservice.repository.CategoryRepository;
import com.finalproject.postservice.repository.CommentRepository;
import com.finalproject.postservice.repository.LikeRepository;
import com.finalproject.postservice.repository.PostRepository;
import com.finalproject.postservice.service.PostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

    @Autowired
    private RestTemplate restTemplate;


    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;
    private final CategoryRepository categoryRepository;


    private static final String USER_PATH = "http://USER-SERVICE/users/user-id-for-post/";

    @Override
    public PostOutput createPost(Post postReq) {
        PostOutput post = new PostOutput();
        User user = restTemplate.getForObject(USER_PATH + postReq.getUserId(), User.class);
        log.info(String.valueOf(postReq.getUserId()));
        log.info(String.valueOf(user));
        Category category = categoryRepository.findById(postReq.getCategoryId()).orElseThrow(() -> {
            throw new RuntimeException("Category not found");
        });

//        check id
        if(postRepository.findById(postReq.getPostId()).isPresent()){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error duplicate Id");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error duplicate id");
        }
        post.setPostId(postReq.getPostId());
        post.setTitle(postReq.getTitle());
        post.setCategory(category);
        post.setContent(postReq.getContent());
        post.setUser(user);
        post.setLikeCounts(postReq.getLikeCount());
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        postRepository.save(postReq);
        return post;
    }

    @Override
    public PostOutput getPostAndUser(String id){
        PostOutput postResponse = new PostOutput();
        Post post = postRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Post not found");
        });
        Category category = categoryRepository.findById(post.getCategoryId()).orElseThrow(()->{
            throw new RuntimeException("Category not found");
        });

        User user = restTemplate.getForObject(USER_PATH+post.getUserId(), User.class);

        postResponse.setPostId(post.getPostId());
        postResponse.setTitle(post.getTitle());
        postResponse.setUser(user);
        postResponse.setCategory(category);
        postResponse.setContent(post.getContent());
        postResponse.setLikeCounts(post.getLikeCount());
        postResponse.setCreatedAt(post.getCreatedAt());
        postResponse.setUpdatedAt(post.getUpdatedAt());

        return postResponse;
    }

    @Override
    public PostDetailedOutput getDetailedPost(String id) {
        PostDetailedOutput postDetailedOutput = new PostDetailedOutput();
        Post post = postRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Post not found");
        });
        Category category = categoryRepository.findById(post.getCategoryId()).orElseThrow(()->{
            throw new RuntimeException("Category not found");
        });

        User user = restTemplate.getForObject(USER_PATH+post.getUserId(), User.class);
        List<Comment> comments = commentRepository.findAll();
        List<Comment> commentOnPostList = new ArrayList<>();
        for (Comment comment: comments) {
            if(comment.getPostId().equals(id)){
                commentOnPostList.add(comment);
            }
        }

        postDetailedOutput.setPostId(post.getPostId());
        postDetailedOutput.setTitle(post.getTitle());
        postDetailedOutput.setContent(post.getContent());
        postDetailedOutput.setCategory(category);
        postDetailedOutput.setUser(user);
        postDetailedOutput.setLikeCounts(post.getLikeCount());
        postDetailedOutput.setCreatedAt(post.getCreatedAt());
        postDetailedOutput.setUpdatedAt(post.getUpdatedAt());
        postDetailedOutput.setCommentOnPostList(commentOnPostList);

        return postDetailedOutput;
    }

    @Override
    public List<Post> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts;
    }

    @Override
    public Post getPost(String id) {
        Post post = postRepository.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post Not Found");
        });
        return post;
    }

    @Override
    public PostOutput updatePost(Post postReq, String id) {
        Post post = postRepository.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post Not Found");
        });
        Category category = categoryRepository.findById(postReq.getCategoryId()).orElseThrow(()->{
            throw new RuntimeException("Category not found");
        });

        User user = restTemplate.getForObject(USER_PATH+post.getUserId(), User.class);
        log.info(id);
        post.setTitle(postReq.getTitle());
        post.setContent(post.getContent());
        post.setCategoryId(postReq.getCategoryId());
        post.setUpdatedAt(LocalDateTime.now());
        postRepository.save(post);

        PostOutput postOutput = new PostOutput();
        postOutput.setPostId(post.getPostId());
        postOutput.setCategory(category);
        postOutput.setContent(postReq.getContent());
        postOutput.setLikeCounts(post.getLikeCount());
        postOutput.setCreatedAt(post.getCreatedAt());
        postOutput.setUpdatedAt(LocalDateTime.now());
        postOutput.setUser(user);
        postOutput.setTitle(postReq.getTitle());

        return postOutput;
    }


    @Override
    public Post deletePost(String id) {
        Post post = postRepository.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found");
        });
        postRepository.deleteById(id);
        return post;
    }

    @Override
    public CommentOutput createComment(Comment req) {
        CommentOutput comment = new CommentOutput();
        User user = restTemplate.getForObject(USER_PATH + req.getUserId(), User.class);
        Post post = postRepository.findById(req.getPostId()).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found");
        });
        if(commentRepository.findById(req.getCommentId()).isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Error duplicate comment id");
        };
        comment.setCommentId(req.getCommentId());
        comment.setContentComment(req.getContentComment());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());
        comment.setPost(post);
        comment.setUser(user);
        commentRepository.save(req);
        return comment;
    }

    @Override
    public CommentOutput updateComment(Comment req, String id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found");
        });
        comment.setContentComment(req.getContentComment());
        comment.setUpdatedAt(LocalDateTime.now());
        commentRepository.save(comment);

        CommentOutput commentOutput = this.getCommentAndUser(id);
        return commentOutput;
    }

    @Override
    public CommentOutput getCommentAndUser(String id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found");
        });
        Post post = postRepository.findById(comment.getPostId()).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found");
        });
        User user = restTemplate.getForObject(USER_PATH + comment.getUserId(), User.class);
        CommentOutput commentOutput = new CommentOutput();
        commentOutput.setCommentId(comment.getCommentId());
        commentOutput.setContentComment(comment.getContentComment());
        commentOutput.setPost(post);
        commentOutput.setUser(user);
        commentOutput.setCreatedAt(comment.getCreatedAt());
        commentOutput.setUpdatedAt(comment.getUpdatedAt());
        return commentOutput;
    }
}
