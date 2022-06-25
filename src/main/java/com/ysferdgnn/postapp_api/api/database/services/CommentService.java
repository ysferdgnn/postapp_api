package com.ysferdgnn.postapp_api.api.database.services;

import com.ysferdgnn.postapp_api.api.requests.CommentPostRequest;
import com.ysferdgnn.postapp_api.api.responses.CommentResponse;
import com.ysferdgnn.postapp_api.api.database.models.Comment;
import com.ysferdgnn.postapp_api.api.database.repos.CommentRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {

    CommentRepository commentRepository;



    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Iterable<Comment> getAllComments(int pageNo,int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return commentRepository.findAll();
    }

    public List<CommentResponse> getAllCommentsByPostIdAsCommentResponse(Long postId,int pageNo,int pageSize){
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        List<Comment> commentList = commentRepository.findAllByPostId(postId,pageable);
        return commentList.stream().map(CommentResponse::new).collect(Collectors.toList());
    }

    public Comment findById(Long commentId) {
        //TODO: implement custom exception
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment saveOneComment(CommentPostRequest commentPostRequest) {
        //TODO: implement validation
        Comment commentToSave = Comment.createCommentFromCommentPostRequest(commentPostRequest);
        commentToSave= commentRepository.save(commentToSave);
        return commentToSave;
    }

    public Comment putOneCommentById(Long commentId, CommentPostRequest commentPostRequest) {
        //TODO: implement converter
        //TODO: implement validator
        //TODO: implement check users exists
        //TODO: implement check post exists
        //TODO: implement check comment exists

        Optional<Comment> optCommentToPut = commentRepository.findById(commentId);
        if (!optCommentToPut.isPresent())
            return null;
        Comment commentToPut = optCommentToPut.get();
        commentToPut.setPostId(commentPostRequest.getPostId());
        commentToPut.setUsersId(commentPostRequest.getUsersId());
        commentToPut.setText(commentPostRequest.getText());
        return  commentRepository.save(commentToPut);

    }

    public void deleteById(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    public List<Comment> selectAllByPostId(Long postId,int pageNo,int pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);

        return commentRepository.findAllByPostId(postId,pageable);
    }
}
