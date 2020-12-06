package com.github.lumunix.fursona.repository;

import com.github.lumunix.fursona.entity.Post;
import com.github.lumunix.fursona.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {
}
