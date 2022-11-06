package com.ysferdgnn.postapp_api.api.database.services;

import com.ysferdgnn.postapp_api.api.database.models.Post;
import com.ysferdgnn.postapp_api.api.database.models.Users;
import com.ysferdgnn.postapp_api.api.database.repository.LikesRepository;
import com.ysferdgnn.postapp_api.api.requests.LikesPostRequest;
import com.ysferdgnn.postapp_api.api.database.models.Likes;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Service
public class LikesService {

    private final LikesRepository likesRepository;
    private final PostService postService;
    private final UsersService usersService;

    public LikesService(LikesRepository likesRepository, PostService postService, UsersService usersService) {
        this.likesRepository = likesRepository;
        this.postService = postService;
        this.usersService = usersService;
    }

    public List<Likes> getAllLikes() {
       return likesRepository.findAll();
    }

    public Likes findById(Long likesId) {
        //TODO: implement custom exception
        return likesRepository.findById(likesId).orElse(null);
    }

    public Likes saveOneLikes(LikesPostRequest likesPostRequest) {
        //TODO: implement converter

        Post post =postService.findById(likesPostRequest.getPostId());
        Users users  = usersService.findById(likesPostRequest.getUsersId());
        Likes likesToSave = new Likes(null,users,post);
       return likesRepository.save(likesToSave);
    }

    public void deleteById(Long likesId) {
        likesRepository.deleteById(likesId);
    }

    public Likes changeLikes(LikesPostRequest likesPostRequest) {
        Post post =postService.findById(likesPostRequest.getPostId());
        Users users  = usersService.findById(likesPostRequest.getUsersId());

        Likes likesToSave = new Likes(null,users,post);
        Likes likesfromDb;
        try{
            likesfromDb= likesRepository.findByUsersAndPost(likesToSave.getUsers(),likesToSave.getPost());
        }catch (NoResultException exception){
            likesfromDb=null;
        }

        if (likesfromDb ==null){
             return  likesRepository.save(likesToSave);
        }else{
            likesRepository.delete(likesfromDb);
            return null;
        }
    }
}
