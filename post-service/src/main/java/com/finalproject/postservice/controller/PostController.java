package com.finalproject.postservice.controller;

//import com.finalproject.postservice.dto.PostResponse;
//import com.finalproject.postservice.mapper.PostMapper;
import com.finalproject.postservice.VO.CommentOutput;
import com.finalproject.postservice.VO.LikeOutput;
import com.finalproject.postservice.VO.PostDetailedOutput;
import com.finalproject.postservice.VO.PostOutput;
import com.finalproject.postservice.model.Category;
import com.finalproject.postservice.model.Comment;
import com.finalproject.postservice.model.Like;
import com.finalproject.postservice.model.Post;
import com.finalproject.postservice.service.CategoryService;
import com.finalproject.postservice.service.CommentService;
import com.finalproject.postservice.service.LikeService;
import com.finalproject.postservice.service.PostService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostOutput> createPost(@RequestBody Post postReq){
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

//    @PostMapping("/likes")
//    public ResponseEntity<LikeOutput> createLike(@RequestBody Like like){
//        LikeOutput likeOutput = likeService.createLike(like);
//        return ResponseEntity.ok(likeOutput);
//    }
//
//    @GetMapping("/likes/{id}")
//    public ResponseEntity<Like> getLike(@PathVariable String id){
//        Like like = likeService.getLike(id);
//        return ResponseEntity.ok(like);
//    }
//
//    @GetMapping("/likes")
//    public ResponseEntity<List<Like>> getAllLikes(){
//        List<Like> likes = likeService.getAllLikes();
//        return ResponseEntity.ok(likes);
//    }
//
//    @GetMapping("/likes/like-user/{id}")
//    public ResponseEntity<LikeOutput> getLikeAndUser(@PathVariable String id){
//        LikeOutput likeOutput = likeService.getLikeAndUser(id);
//        return ResponseEntity.ok(likeOutput);
//    }
//
//    @DeleteMapping("/likes/{id}")
//    public ResponseEntity<Like> deleteLike(@PathVariable String id){
//        Like like = likeService.deleteLike(id);
//        return ResponseEntity.ok(like);
//    }
//
//
//    @PostMapping("/comments")
//    public ResponseEntity<CommentOutput> createComment(@RequestBody Comment req){
//        CommentOutput commentOutput = commentService.createComment(req);
//        return ResponseEntity.ok(commentOutput);
//    }
//
//    @GetMapping("/comments/{id}")
//    public ResponseEntity<Comment> getComment(@PathVariable String id){
//        Comment comment = commentService.getComment(id);
//        return ResponseEntity.ok(comment);
//    }
//
//    @GetMapping("/comments")
//    public ResponseEntity<List<Comment>> getAllComment(){
//        List<Comment> allComment = commentService.getAllComment();
//        return ResponseEntity.ok(allComment);
//    }
//
//    @GetMapping("/comments/comment-user/{id}")
//    public ResponseEntity<CommentOutput> getCommentAndUser(@PathVariable String id){
//        CommentOutput commentOutput = commentService.getCommentAndUser(id);
//        return ResponseEntity.ok(commentOutput);
//    }
//
//    @PatchMapping("/comments/{id}")
//    public ResponseEntity<CommentOutput> updateComment(@RequestBody Comment req, @PathVariable String id){
//        CommentOutput commentOutput = commentService.updateComment(req, id);
//        return ResponseEntity.ok(commentOutput);
//    }
//
//    @DeleteMapping("/comments/{id}")
//    public ResponseEntity<Comment> deleteComment(@PathVariable String id){
//        Comment comment = commentService.deleteComment(id);
//        return ResponseEntity.ok(comment);
//    }
//
//    @PostMapping("/categories")
//    public ResponseEntity<Category> createCategory(@RequestBody Category req){
//        Category categoryCreated = categoryService.createCategory(req);
//        return ResponseEntity.ok(categoryCreated);
//    }
//
//    @GetMapping("/categories/{id}")
//    public ResponseEntity<Category> getCategory(@PathVariable String id){
//        Category category = categoryService.getCategory(id);
//        return ResponseEntity.ok(category);
//    }
//
//    @GetMapping("/categories")
//    public ResponseEntity<List<Category>> getAllCategories(){
//        List<Category> categories = categoryService.getAllCategories();
//        return ResponseEntity.ok(categories);
//    }
//
//    @PatchMapping("/categories/{id}")
//    public ResponseEntity<Category> updateCategory(@RequestBody Category category, @PathVariable String id){
//        Category categoryUpdated = categoryService.updateCategory(category, id);
//        return ResponseEntity.ok(categoryUpdated);
//    }
//
//    @DeleteMapping("/categories/{id}")
//    public ResponseEntity<?> deleteCategory(@PathVariable String id){
//        categoryService.deleteCategory(id);
//        return ResponseEntity.ok(Boolean.TRUE);
//    }

}
