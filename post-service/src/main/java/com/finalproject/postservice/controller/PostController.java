package com.finalproject.postservice.controller;

//import com.finalproject.postservice.dto.PostResponse;
//import com.finalproject.postservice.mapper.PostMapper;
import com.finalproject.postservice.VO.CommentOutput;
import com.finalproject.postservice.VO.PostDetailedOutput;
import com.finalproject.postservice.VO.PostOutput;
import com.finalproject.postservice.model.Comment;
import com.finalproject.postservice.model.Post;
import com.finalproject.postservice.service.PostService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;
@Log4j2
@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;


    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody Post postReq){
        PostOutput postCreated = postService.createPost(postReq);
        return ResponseEntity.ok(postCreated);
//        try {
//            return ResponseEntity.ok(postCreated);
//        }catch (Exception e){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error duplicate Id");
//        }
    }

    @GetMapping("/post-user/{postId}")
    public ResponseEntity<PostOutput> getPostAndUser(@PathVariable String postId){
        PostOutput postResponse = postService.getPostAndUser(postId);
        return ResponseEntity.ok(postResponse);
    }

    @GetMapping("/post-detailed/{id}")
    public ResponseEntity<PostDetailedOutput> getPostDetailed(@PathVariable String id){
        PostDetailedOutput postDetailedOutput = postService.getDetailedPost(id);
        return ResponseEntity.ok(postDetailedOutput);
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(){
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable String id){
        Post post = postService.getPost(id);
        return ResponseEntity.ok(post);
    }

//    @PatchMapping("/{id}")
//    public ResponseEntity<PostOutput> updatePost(@PathVariable String id, @RequestBody Post postReq){
//        PostOutput postOutput = postService.updatePost(postReq, id);
//        return ResponseEntity.ok(postOutput);
//    }
    @PatchMapping("/{id}")
    public ResponseEntity<PostOutput> updatePost(@PathVariable String id, @RequestBody Post post){
        log.info(id);
        PostOutput postOutput = postService.updatePost(post, id);
        return ResponseEntity.ok(postOutput);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable String id){
        Post post = postService.deletePost(id);
        return ResponseEntity.ok(post);
    }




}
