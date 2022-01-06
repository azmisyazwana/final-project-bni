package com.finalproject.postservice.service;

import com.finalproject.postservice.VO.PostDetailedOutput;
import com.finalproject.postservice.VO.PostOutput;
//import com.finalproject.postservice.dto.PostResponse;
import com.finalproject.postservice.model.Post;

import java.util.List;

public interface PostService {
    PostOutput createPost(Post postReq);
    PostOutput getPostAndUser(String id);
    PostDetailedOutput getDetailedPost(String id);
    List<Post> getAllPosts();
    Post getPost(String id);
    PostOutput updatePost(Post postReq, String id);
    Post deletePost(String id);

}
