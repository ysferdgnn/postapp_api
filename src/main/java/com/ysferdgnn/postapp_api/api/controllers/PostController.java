package com.ysferdgnn.postapp_api.api.controllers;

import com.ysferdgnn.postapp_api.api.database.models.Post;
import com.ysferdgnn.postapp_api.api.requests.PostPostRequest;
import com.ysferdgnn.postapp_api.api.responses.PostAndLikesDto;

import com.ysferdgnn.postapp_api.api.database.services.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.util.Streamable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/post")
@Api(value = "Post api controller")

public class PostController {

    PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    @ApiOperation(value = "get all posts")
    public List<PostAndLikesDto> getAllPosts(){
       Iterable<Post> postListIterable = postService.getAllPosts();
        List<Post> postList = Streamable.of(postListIterable).toList();
      List<PostAndLikesDto> dtoList= postList.stream().map(PostAndLikesDto::new).collect(Collectors.toList());
      return dtoList;
    }

    @ApiOperation(value = "find one post by postId")
    @GetMapping("/{postId}")
    public  Post findOnePost(@PathVariable Long postId){
       return postService.findById(postId);
    }

    @ApiOperation(value = "save post with post request model")
    @PostMapping
    public Post saveOnePost(@RequestBody PostPostRequest postPostRequest){
       return postService.saveOnePost(postPostRequest);
    }

    @ApiOperation(value = "put one post with postId and post request model")
    @PutMapping("/{postId}")
    public Post putOnePost(@PathVariable Long postId, @RequestBody PostPostRequest postPostRequest){
        return postService.putPostById(postId,postPostRequest);
    }
    @ApiOperation(value = "delete one post with postId")
    @DeleteMapping("/{postId}")
    public  void deleteOnePost(@PathVariable Long postId)
    {



        postService.deleteById(postId);
    }
}
