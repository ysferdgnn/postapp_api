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
        Users usersToSave = new Users();
        usersToSave.setUsername(usersPostRequest.getUsername());
        usersToSave.setName(usersPostRequest.getName());
        usersToSave.setPassword(usersPostRequest.getPassword());
        usersToSave.setCreatedAt(timeUtils.getTimestampNow());
        usersToSave.setUpdatedAt(timeUtils.getTimestampNow());

        return  usersRepository.save(usersToSave);
    }
    public Users saveOneUsers(Users users){
        users.setUpdatedAt(new Timestamp(new Date().getTime()));
        users.setCreatedAt(new Timestamp(new Date().getTime()));
        return usersRepository.save(users);
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
        Optional<Users> optUsersToUpdate = usersRepository.findById(usersId);

        if (!optUsersToUpdate.isPresent()){
            return null;
        }
        Users usersToUpdate =optUsersToUpdate.get();
        usersToUpdate.setUsername(usersPostRequest.getUsername());
        usersToUpdate.setPassword(usersPostRequest.getPassword());
        usersToUpdate.setName(usersPostRequest.getName());
        usersToUpdate.setUpdatedAt(timeUtils.getTimestampNow());

        return usersRepository.save(usersToUpdate);
    }

    public Users findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }
}
