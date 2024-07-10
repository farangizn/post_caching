package org.example.module10homework2.controller;

import lombok.RequiredArgsConstructor;
import org.example.module10homework2.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @GetMapping("/cached")
    public String getAll(Model model) {
        long start = System.currentTimeMillis();
        model.addAttribute("posts", postService.getAllCached());
        long end = System.currentTimeMillis();

        System.out.println("Getting posts from cache took: " + (end - start)  + " ms");
        model.addAttribute("message", "Getting posts from cache took: " + (end - start) + " ms");

        return "index";
    }

    @GetMapping
    public String get(Model model) {
        long start = System.currentTimeMillis();
        model.addAttribute("posts", postService.getAll());
        long end = System.currentTimeMillis();

        System.out.println("Getting posts took: " + (end - start)  + " ms");
        model.addAttribute("message", "Getting posts took: " + (end - start) + " ms");

        return "index";
    }
}
