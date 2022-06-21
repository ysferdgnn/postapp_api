package com.ysferdgnn.postapp_api.api.database.services;

import com.ysferdgnn.postapp_api.api.requests.PostPostRequest;
import com.ysferdgnn.postapp_api.api.database.models.Post;
import com.ysferdgnn.postapp_api.api.database.repos.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

    PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Iterable<Post> getAllPosts() {
        return  postRepository.findAll();
    }

    public Post findById(Long postId) {
        //TODO: implement custom exception
        return postRepository.findById(postId).orElse(null);
    }

    public Post saveOnePost(PostPostRequest postPostRequest) {

        //TODO: implement validation
        //TODO: implement converter class
        Post postToSave = new Post();
        postToSave.setText(postPostRequest.getText());
        postToSave.setTitle(postPostRequest.getTitle());
        postToSave.setUsersId(postPostRequest.getUsersId());

        return postRepository.save(postToSave);
    }

    public Post putPostById(Long postId, PostPostRequest postPostRequest) {
        //TODO: implement validation
        //TODO: implement converter class
        //TODO: implement custom exception

        Optional<Post> optPostToPut = postRepository.findById(postId);
        if (!optPostToPut.isPresent()){
            return null;
        }
        Post postToPut = optPostToPut.get();
        postToPut.setUsersId(postPostRequest.getUsersId());
        postToPut.setTitle(postPostRequest.getTitle());
        postToPut.setText(postPostRequest.getText());
       return  postRepository.save(postToPut);
    }

    public void deleteById(Long postId) {

        postRepository.deleteById(postId);
    }
}
