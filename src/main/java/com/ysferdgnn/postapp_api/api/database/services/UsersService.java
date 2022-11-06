package com.ysferdgnn.postapp_api.api.database.services;

import com.ysferdgnn.postapp_api.api.database.repository.UsersRepository;
import com.ysferdgnn.postapp_api.api.requests.UsersPostRequest;
import com.ysferdgnn.postapp_api.api.database.models.Users;
import com.ysferdgnn.postapp_api.utils.TimeUtils;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    UsersRepository usersRepository;
    TimeUtils timeUtils;

    public UsersService(UsersRepository usersRepository,TimeUtils timeUtils) {
        this.usersRepository = usersRepository;
        this.timeUtils = timeUtils;
    }

    public  Users saveOneUsers(UsersPostRequest usersPostRequest) {
        //implements validation
        //implements convertion class

        Users usersToSave = new Users(
                null,
                usersPostRequest.getName(),
                usersPostRequest.getUsername(),
                usersPostRequest.getPassword(),
                timeUtils.getTimestampNow(),
                timeUtils.getTimestampNow(),
                null,
                null,
                null);
        return  usersRepository.save(usersToSave);
    }
    public Users saveOneUsers(Users users){
        Users usersToSave =users
                .copy(
                        users.getId(),
                        users.getName(),
                        users.getUsername(),
                        users.getPassword(),
                        users.getCreatedAt(),
                        users.getUpdatedAt(),
                        users.getComments(),
                        users.getPosts(),
                        users.getLikes());
        return usersRepository.save(usersToSave);
    }

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Users findById(Long userId) {
       return usersRepository.findById(userId).orElse(null);
    }

    public void deleteById(Long usersId) {
        usersRepository.deleteById(usersId);
    }

    public Users putUsersById(Long usersId, UsersPostRequest usersPostRequest) {
        //TODO: implements validation
        //TODO: implements check user
        //TODO: implements request to users converter
        //TODO: implements custom exception class
        Optional<Users> optUsersFound = usersRepository.findById(usersId);

        if (!optUsersFound.isPresent()){
            return null;
        }

//        usersToUpdate.setUsername(usersPostRequest.getUsername());
//        usersToUpdate.setPassword(usersPostRequest.getPassword());
//        usersToUpdate.setName(usersPostRequest.getName());
//        usersToUpdate.setUpdatedAt(timeUtils.getTimestampNow());
        Users usersToUpdate = new Users(
                optUsersFound.get().getId(),
                usersPostRequest.getName(),
                usersPostRequest.getUsername(),
                usersPostRequest.getPassword(),
                optUsersFound.get().getCreatedAt(),
                timeUtils.getTimestampNow(),
                null,
                null,
                null);

        return usersRepository.save(usersToUpdate);
    }

    public Users findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }
}
