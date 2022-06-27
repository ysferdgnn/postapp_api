package com.ysferdgnn.postapp_api.api.database.services;

import com.ysferdgnn.postapp_api.api.database.repos.concretes.PostRepositoryImpl;
import com.ysferdgnn.postapp_api.api.requests.PostPostRequest;
import com.ysferdgnn.postapp_api.api.database.models.Post;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

    PostRepositoryImpl postRepositoryImpl;

    public PostService(PostRepositoryImpl postRepository) {
        this.postRepositoryImpl = postRepository;
    }

    public Iterable<Post> getAllPosts() {
        return  postRepositoryImpl.findAll();
    }

    public Post findById(Long postId) {
        //TODO: implement custom exception
        return postRepositoryImpl.findById(postId).orElse(null);
    }

    public Post saveOnePost(PostPostRequest postPostRequest) {

        //TODO: implement validation
        //TODO: implement converter class
        Post postToSave = new Post();
        postToSave.setText(postPostRequest.getText());
        postToSave.setTitle(postPostRequest.getTitle());
        postToSave.setUsersId(postPostRequest.getUsersId());

        return postRepositoryImpl.save(postToSave);
    }

    public Post putPostById(Long postId, PostPostRequest postPostRequest) {
        //TODO: implement validation
        //TODO: implement converter class
        //TODO: implement custom exception

        Optional<Post> optPostToPut = postRepositoryImpl.findById(postId);
        if (!optPostToPut.isPresent()){
            return null;
        }
        Post postToPut = optPostToPut.get();
        postToPut.setUsersId(postPostRequest.getUsersId());
        postToPut.setTitle(postPostRequest.getTitle());
        postToPut.setText(postPostRequest.getText());
       return  postRepositoryImpl.save(postToPut);
    }

    public void deleteById(Long postId) {

        postRepositoryImpl.deleteById(postId);
    }
}
