package com.ysferdgnn.postapp_api.api.database.services;

import com.ysferdgnn.postapp_api.api.database.models.Users;
import com.ysferdgnn.postapp_api.api.database.repository.PostRepository;
import com.ysferdgnn.postapp_api.api.requests.PostPostRequest;
import com.ysferdgnn.postapp_api.api.database.models.Post;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UsersService usersService;

    public PostService(PostRepository postRepository, UsersService usersService) {
        this.postRepository= postRepository;
        this.usersService = usersService;
    }

    public Iterable<Post> getAllPosts() {
        return  postRepository.findAll();
    }

    public Post findById(Long postId) {
        //TODO: implement custom exception
        return postRepository.findById(postId).orElse(null);
    }

    public Post saveOnePost(PostPostRequest postPostRequest) {

        Users user = usersService.findById(postPostRequest.getUsersId());
        //TODO: implement validation
        //TODO: implement converter class


        Post postToSave = new Post(null,postPostRequest.getTitle(),postPostRequest.getText(),user);
        return postRepository.save(postToSave);
    }

    public Post putPostById(Long postId, PostPostRequest postPostRequest) {
        //TODO: implement validation
        //TODO: implement converter class
        //TODO: implement custom exception

        Optional<Post> optPostFound = postRepository.findById(postId);
        if (!optPostFound.isPresent()){
            return null;
        }
        Users users = usersService.findById(postPostRequest.getUsersId()); //TODO: validate!
        Post postToPut = new Post(null,postPostRequest.getTitle(),postPostRequest.getText(),users);
       return  postRepository.save(postToPut);
    }

    public void deleteById(Long postId) {

        postRepository.deleteById(postId);
    }
}
