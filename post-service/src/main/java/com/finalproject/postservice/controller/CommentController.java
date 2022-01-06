package com.finalproject.postservice.controller;

import com.finalproject.postservice.VO.CommentOutput;
import com.finalproject.postservice.model.Category;
import com.finalproject.postservice.model.Comment;
import com.finalproject.postservice.service.CommentService;
import com.finalproject.postservice.service.PostService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final PostService postService;
    @PostMapping
    public ResponseEntity<CommentOutput> createComment(@RequestBody Comment req){
        CommentOutput commentOutput = postService.createComment(req);
        return ResponseEntity.ok(commentOutput);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable String id){
        Comment comment = commentService.getComment(id);
        return ResponseEntity.ok(comment);
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getAllComment(){
        List<Comment> allComment = commentService.getAllComment();
        return ResponseEntity.ok(allComment);
    }

    @GetMapping("comment-user/{id}")
    public ResponseEntity<CommentOutput> getCommentAndUser(@PathVariable String id){
        CommentOutput commentOutput = postService.getCommentAndUser(id);
        return ResponseEntity.ok(commentOutput);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CommentOutput> updateComment(@RequestBody Comment req, @PathVariable String id){
        CommentOutput commentOutput = postService.updateComment(req, id);
        return ResponseEntity.ok(commentOutput);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable String id){
        Comment comment = commentService.deleteComment(id);
        return ResponseEntity.ok(comment);
    }

}
