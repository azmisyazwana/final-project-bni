package com.finalproject.postservice.service;

import com.finalproject.postservice.VO.CommentOutput;
import com.finalproject.postservice.model.Comment;

import java.util.List;

public interface CommentService {
//    CommentOutput createComment(Comment req);
    Comment getComment(String id);
//    CommentOutput getCommentAndUser(String id);
    List<Comment> getAllComment();
//    CommentOutput updateComment(Comment req, String id);
    Comment deleteComment(String id);
}
