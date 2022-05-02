package com.ysferdgnn.postapp_api.api.controllers;


import com.ysferdgnn.postapp_api.api.Requests.LikesPostRequest;
import com.ysferdgnn.postapp_api.api.database.models.Likes;
import com.ysferdgnn.postapp_api.api.database.services.LikesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
@Api(value = "likes api controller")
public class LikesController {

    LikesService likesService;

    public LikesController(LikesService likesService) {
        this.likesService = likesService;
    }

    @GetMapping
    @ApiOperation(value = "get all likes")
    public List<Likes> getAllLikes(){
        return  likesService.getAllLikes();
    }
    @ApiOperation(value = "find one likes with likesId")
    @GetMapping("/{likesId}")
    public Likes findOneLikes(@PathVariable Long likesId){
        return likesService.findById(likesId);
    }

    @PostMapping
    @ApiOperation(value = "save one likes with likes post request model")
    public Likes saveOneLikes(@RequestBody LikesPostRequest likesPostRequest){
        return likesService.saveOneLikes(likesPostRequest);
    }

    @PutMapping("/{likesId}")
    @ApiOperation(value = "put one likes with likesId and likes request model")
    public Likes putOneLikes(@PathVariable Long likesId,@RequestBody LikesPostRequest likesPostRequest){
        return likesService.putLikesById(likesId,likesPostRequest);
    }

    @DeleteMapping("/{likesId}")
    @ApiOperation(value = "delete one likes with likesId")
    public void deleteOneLikes(@PathVariable Long likesId){
        likesService.deleteById(likesId);
    }


}
