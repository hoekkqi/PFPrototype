package com.github.lumunix.fursona.controller;



import com.github.lumunix.fursona.entity.Post;
import com.github.lumunix.fursona.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("/addPost")
    public String main(Model model) {
        model.addAttribute("post", new Post());
        return "index";
    }

    @PostMapping("/addPost")
    public String save(Post post, Model model) {
        model.addAttribute("post", post);
        postService.savePost(post);
        System.out.println("Saved");
        return "saved";
    }
}