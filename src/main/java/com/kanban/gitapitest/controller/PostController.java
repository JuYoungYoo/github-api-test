package com.kanban.gitapitest.controller;

import com.kanban.gitapitest.domain.Post;
import com.kanban.gitapitest.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {


    private final PostService postService;

    @PostMapping("/")
    public ResponseEntity<Void> createPost(@RequestBody Post post,
                                           UriComponentsBuilder builder) {
        boolean flag = postService.createPost(post);
        if (flag == false) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("index").buildAndExpand(post.getId()).toUri());
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findByPost(@PathVariable Long id) {
        Optional<Post> user = postService.getPost(id);
        if(user.isPresent()){	//exist
            return new ResponseEntity<Post>(user.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Post> update(@PathVariable Long id, @RequestBody Post post) {
        Post updateUser = postService.updatePost(id, post);
        if(updateUser != null){	//exist
            return new ResponseEntity<Post>(updateUser, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
        }
    }



}