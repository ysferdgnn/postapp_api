package com.ysferdgnn.postapp_api.api.controllers;

import com.ysferdgnn.postapp_api.api.requests.CommentPostRequest;
import com.ysferdgnn.postapp_api.api.responses.CommentResponse;
import com.ysferdgnn.postapp_api.api.database.models.Comment;
import com.ysferdgnn.postapp_api.api.database.services.CommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.util.Streamable;
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

    @GetMapping("/{pageNo}/{pageSize}")
    public List<Comment> getAllComments(@PathVariable int pageNo,@PathVariable int pageSize){

        return Streamable.of(commentService.getAllComments(pageNo,pageSize)).toList();
    }

    @GetMapping("/{commentId}")
    public Comment findCommentById(@PathVariable Long commentId){
        return commentService.findById(commentId);
    }

    @GetMapping("/postId={postId}/{pageNo}/{pageSize}")
    public List<CommentResponse> getAllCommentsByPostId(@PathVariable Long postId,@PathVariable int pageNo,@PathVariable int pageSize){
        return commentService.getAllCommentsByPostIdAsCommentResponse(postId,pageNo,pageSize);
    }

    @PostMapping
    public CommentResponse saveOneComment(@RequestBody CommentPostRequest commentPostRequest){
        Comment comment = commentService.saveOneComment(commentPostRequest);
        return  CommentResponse.createFromComment(comment);

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
