package com.finalproject.postservice.service;

import com.finalproject.postservice.VO.LikeOutput;
import com.finalproject.postservice.model.Like;

import java.util.List;

public interface LikeService {
    LikeOutput createLike(Like req);
    Like getLike(String id);
    List<Like> getAllLikes();
    LikeOutput getLikeAndUser(String id);
    Like deleteLike(String id);
}
