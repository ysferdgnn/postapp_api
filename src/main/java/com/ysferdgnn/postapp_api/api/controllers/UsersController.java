package com.ysferdgnn.postapp_api.api.controllers;

import com.ysferdgnn.postapp_api.api.Requests.UsersPostRequest;
import com.ysferdgnn.postapp_api.api.database.models.Users;
import com.ysferdgnn.postapp_api.api.database.services.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Api(value = "User Api Documentation")
public class UsersController {

    UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    @ApiOperation(value = "get All User ")
    public List<Users> getAllUsers(){
        return usersService.getAllUsers();
    }

    @GetMapping("/{usersId}")
    @ApiOperation(value = "find One User with Id")
    public Users findOneUsers(@PathVariable Long usersId){
        return usersService.findById(usersId);
    }

    @PostMapping
    @ApiOperation(value = "save One User with request model")
    public Users saveOneUsers(@RequestBody UsersPostRequest usersPostRequest){
        return usersService.saveOneUsers(usersPostRequest);
    }

    @PutMapping("/{usersId}")
    @ApiOperation(value = "Update one user with usersId and request mode")
    public  Users putUsersById(@PathVariable Long usersId,@RequestBody UsersPostRequest usersPostRequest){
        return usersService.putUsersById(usersId,usersPostRequest);
    }

    @DeleteMapping("/{usersId}")
    @ApiOperation(value = "delete one user with usersId")
    public void deleteOneUsers(@PathVariable Long usersId){
        usersService.deleteById(usersId);
    }

}
