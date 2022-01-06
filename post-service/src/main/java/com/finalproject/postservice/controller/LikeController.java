package com.finalproject.postservice.controller;

import com.finalproject.postservice.VO.LikeOutput;
import com.finalproject.postservice.model.Like;
import com.finalproject.postservice.service.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likes")
@AllArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<LikeOutput> createLike(@RequestBody Like like){
        LikeOutput likeOutput = likeService.createLike(like);
        return ResponseEntity.ok(likeOutput);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Like> getLike(@PathVariable String id){
        Like like = likeService.getLike(id);
        return ResponseEntity.ok(like);
    }

    @GetMapping
    public ResponseEntity<List<Like>> getAllLikes(){
        List<Like> likes = likeService.getAllLikes();
        return ResponseEntity.ok(likes);
    }

    @GetMapping("/like-user/{id}")
    public ResponseEntity<LikeOutput> getLikeAndUser(@PathVariable String id){
        LikeOutput likeOutput = likeService.getLikeAndUser(id);
        return ResponseEntity.ok(likeOutput);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Like> deleteLike(@PathVariable String id){
        Like like = likeService.deleteLike(id);
        return ResponseEntity.ok(like);
    }
}
