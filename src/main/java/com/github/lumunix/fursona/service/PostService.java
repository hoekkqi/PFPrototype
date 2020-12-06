package com.github.lumunix.fursona.service;



import com.github.lumunix.fursona.entity.Post;
import com.github.lumunix.fursona.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    public void savePost(Post post) {

        postRepository.save(post);
    }

}
