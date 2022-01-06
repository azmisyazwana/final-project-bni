package com.finalproject.postservice.repository;

import com.finalproject.postservice.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String > {
}
