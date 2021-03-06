package com.ysferdgnn.postapp_api.api.database.services;

import com.ysferdgnn.postapp_api.api.database.repos.concretes.LikesRepositoryImpl;
import com.ysferdgnn.postapp_api.api.requests.LikesPostRequest;
import com.ysferdgnn.postapp_api.api.database.models.Likes;
import com.ysferdgnn.postapp_api.api.database.repos.abstracts.LikesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikesService {

    LikesRepositoryImpl likesRepositoryImpl;

    public LikesService(LikesRepositoryImpl likesRepositoryImpl) {
        this.likesRepositoryImpl = likesRepositoryImpl;
    }

    public List<Likes> getAllLikes() {
       return likesRepositoryImpl.findAll();
    }

    public Likes findById(Long likesId) {
        //TODO: implement custom exception
        return likesRepositoryImpl.findById(likesId).orElse(null);
    }

    public Likes saveOneLikes(LikesPostRequest likesPostRequest) {
        //TODO: implement converter
        Likes likesToSave = new Likes();
        likesToSave.setPostId(likesPostRequest.getPostId());
        likesToSave.setUsersId(likesPostRequest.getUsersId());
       return likesRepositoryImpl.save(likesToSave);
    }



    public Likes putLikesById(Long likesId, LikesPostRequest likesPostRequest) {
        //TODO: implement converter
        //TODO: implement check user
        //TODO: implement custom exception

        Optional<Likes> optLikesToUpdate = likesRepositoryImpl.findById(likesId);

        if (!optLikesToUpdate.isPresent()){
            return null;
        }
       Likes likesToUpdate= optLikesToUpdate.get();
        likesToUpdate.setUsersId(likesPostRequest.getUsersId());
        likesToUpdate.setPostId(likesPostRequest.getPostId());
       return likesRepositoryImpl.save(likesToUpdate);


    }

    public void deleteById(Long likesId) {
        likesRepositoryImpl.deleteById(likesId);
    }

    public Likes changeLikes(LikesPostRequest likesPostRequest) {
        Likes likesToSave = new Likes();
        likesToSave.setPostId(likesPostRequest.getPostId());
        likesToSave.setUsersId(likesPostRequest.getUsersId());

        Likes likesfromDb = likesRepositoryImpl.findByUsersIdAndPostId(likesToSave.getUsersId(),likesToSave.getPostId());

        if (likesfromDb ==null){
             return  likesRepositoryImpl.save(likesToSave);
        }else{
            likesRepositoryImpl.delete(likesfromDb);
            return null;
        }
    }
}
