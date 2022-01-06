package com.finalproject.postservice.service.impl;

import com.finalproject.postservice.VO.CommentOutput;
import com.finalproject.postservice.VO.User;
import com.finalproject.postservice.model.Comment;
import com.finalproject.postservice.model.Post;
import com.finalproject.postservice.repository.CommentRepository;
import com.finalproject.postservice.repository.PostRepository;
import com.finalproject.postservice.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

    private static final String USER_PATH = "http://localhost:8080/users/user-id-for-post/";

    @Autowired
    private RestTemplate restTemplate;

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

//    @Override
//    public CommentOutput createComment(Comment req) {
//        CommentOutput comment = new CommentOutput();
//        User user = restTemplate.getForObject(USER_PATH + req.getUserId(), User.class);
//        Post post = postRepository.findById(req.getPostId()).orElseThrow(() -> {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found");
//        });
//        if(commentRepository.findById(req.getCommentId()).isPresent()){
//            throw new ResponseStatusException(HttpStatus.CONFLICT, "Error duplicate comment id");
//        };
//        comment.setCommentId(req.getCommentId());
//        comment.setContentComment(req.getContentComment());
//        comment.setCreatedAt(LocalDateTime.now());
//        comment.setUpdatedAt(LocalDateTime.now());
//        comment.setPost(post);
//        comment.setUser(user);
//        commentRepository.save(req);
//        return comment;
//    }

    @Override
    public Comment getComment(String id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found");
        });
        return comment;
    }

//    @Override
//    public CommentOutput getCommentAndUser(String id) {
//        Comment comment = this.getComment(id);
//        Post post = postRepository.findById(comment.getPostId()).orElseThrow(() -> {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found");
//        });
//        User user = restTemplate.getForObject(USER_PATH + comment.getUserId(), User.class);
//        CommentOutput commentOutput = new CommentOutput();
//        commentOutput.setCommentId(comment.getCommentId());
//        commentOutput.setContentComment(comment.getContentComment());
//        commentOutput.setPost(post);
//        commentOutput.setUser(user);
//        commentOutput.setCreatedAt(comment.getCreatedAt());
//        commentOutput.setUpdatedAt(comment.getUpdatedAt());
//        return commentOutput;
//    }

    @Override
    public List<Comment> getAllComment() {
        List<Comment> comments = commentRepository.findAll();
        return comments;
    }

//    @Override
//    public CommentOutput updateComment(Comment req, String id) {
//        Comment comment = this.getComment(id);
//        comment.setContentComment(req.getContentComment());
//        comment.setUpdatedAt(LocalDateTime.now());
//        commentRepository.save(comment);
//
//        CommentOutput commentOutput = this.getCommentAndUser(id);
//        return commentOutput;
//    }

    @Override
    public Comment deleteComment(String id) {
        Comment comment = this.getComment(id);
        Post post = postRepository.findById(comment.getPostId()).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post Not Found");
        });
        commentRepository.deleteById(id);
        return comment;
    }
}
