package com.ysferdgnn.postapp_api.api.controllers;

import com.ysferdgnn.postapp_api.api.requests.CommentPostRequest;
import com.ysferdgnn.postapp_api.api.responses.CommentResponse;
import com.ysferdgnn.postapp_api.api.database.models.Comment;
import com.ysferdgnn.postapp_api.api.database.services.CommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@ApiOperation(value = "Comment api controller")

public class CommentController {

    CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getAllComments(){
        return commentService.getAllComments();
    }

    @GetMapping("/{commentId}")
    public Comment findCommentById(@PathVariable Long commentId){
        return commentService.findById(commentId);
    }

    @GetMapping("/postId={postId}")
    public List<CommentResponse> getAllCommentsByPostId(@PathVariable Long postId){
        return commentService.getAllCommentsByPostIdAsCommentResponse(postId);
    }

    @PostMapping
    public Comment saveOneComment(@RequestBody CommentPostRequest commentPostRequest){
        return commentService.saveOneComment(commentPostRequest);
    }

    @PutMapping("/{commentId}")
    public Comment putOneComment(@PathVariable Long commentId,@RequestBody CommentPostRequest commentPostRequest){
        return commentService.putOneCommentById(commentId,commentPostRequest);
    }

    @DeleteMapping("/{commentId}")
    public void deleteOneComment(@PathVariable Long commentId){
        commentService.deleteById(commentId);
    }
}
