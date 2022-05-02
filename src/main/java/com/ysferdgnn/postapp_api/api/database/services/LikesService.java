package com.ysferdgnn.postapp_api.api.database.services;

import com.ysferdgnn.postapp_api.api.Requests.LikesPostRequest;
import com.ysferdgnn.postapp_api.api.database.models.Likes;
import com.ysferdgnn.postapp_api.api.database.repos.LikesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikesService {

    LikesRepository likesRepository;

    public LikesService(LikesRepository likesRepository) {
        this.likesRepository = likesRepository;
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
        Likes likesToSave = new Likes();
        likesToSave.setPostId(likesPostRequest.getPostId());
        likesToSave.setUsersId(likesPostRequest.getUsersId());
       return likesRepository.save(likesToSave);
    }

    public Likes putLikesById(Long likesId, LikesPostRequest likesPostRequest) {
        //TODO: implement converter
        //TODO: implement check user
        //TODO: implement custom exception

        Optional<Likes> optLikesToUpdate = likesRepository.findById(likesId);

        if (!optLikesToUpdate.isPresent()){
            return null;
        }
       Likes likesToUpdate= optLikesToUpdate.get();
        likesToUpdate.setUsersId(likesPostRequest.getUsersId());
        likesToUpdate.setPostId(likesPostRequest.getPostId());
       return likesRepository.save(likesToUpdate);


    }

    public void deleteById(Long likesId) {
        likesRepository.deleteById(likesId);
    }
}
