package com.kanban.gitapitest.service;

import com.kanban.gitapitest.domain.Post;
import com.kanban.gitapitest.repository.PostRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public boolean createPost(Post post) {
        post.setRegDate(LocalDateTime.now());
        try{
            postRepository.save(post);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public Post updatePost(Long id, Post post) {
        final Optional<Post> oldPost = postRepository.findById(id);
        if(oldPost.isPresent()){
            post.setId(id);
            return postRepository.save(post);
        }
        else{
            return null;
        }
    }

    public boolean deletePost(Long id) {
        final Optional<Post> fetchedUser = postRepository.findById(id);
        if(fetchedUser.isPresent()){
            postRepository.deleteById(id);
            return true;
        }
        else{
            return false;
        }
    }

    public Optional<Post> getPost(Long id){
        return postRepository.findById(id);
    }
}