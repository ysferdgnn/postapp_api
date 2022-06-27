package com.ysferdgnn.postapp_api.api.database.services;

import com.ysferdgnn.postapp_api.api.database.repos.concretes.CommentRepositoryImpl;
import com.ysferdgnn.postapp_api.api.requests.CommentPostRequest;
import com.ysferdgnn.postapp_api.api.responses.CommentResponse;
import com.ysferdgnn.postapp_api.api.database.models.Comment;
import com.ysferdgnn.postapp_api.api.database.repos.abstracts.CommentRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {

    CommentRepositoryImpl commentRepositoryImpl;



    public CommentService(CommentRepositoryImpl commentRepository) {
        this.commentRepositoryImpl = commentRepository;
    }

    public Iterable<Comment> getAllComments(int pageNo,int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return commentRepositoryImpl.findAll();
    }

    public List<CommentResponse> getAllCommentsByPostIdAsCommentResponse(Long postId,int pageNo,int pageSize){
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        List<Comment> commentList = commentRepositoryImpl.findAllByPostId(postId,pageable);
        List<CommentResponse> responseList = commentList.stream().map(CommentResponse::createFromComment).collect(Collectors.toList());
        return responseList;
    }

    public Comment findById(Long commentId) {
        //TODO: implement custom exception
        return commentRepositoryImpl.findById(commentId).orElse(null);
    }

    @Transactional
    public Comment saveOneComment(CommentPostRequest commentPostRequest) {
        //TODO: implement validation
        Comment commentToSave = Comment.createCommentFromCommentPostRequest(commentPostRequest);
        return commentRepositoryImpl.save(commentToSave);

    }

    public Comment putOneCommentById(Long commentId, CommentPostRequest commentPostRequest) {
        //TODO: implement converter
        //TODO: implement validator
        //TODO: implement check users exists
        //TODO: implement check post exists
        //TODO: implement check comment exists

        Optional<Comment> optCommentToPut = commentRepositoryImpl.findById(commentId);
        if (!optCommentToPut.isPresent())
            return null;
        Comment commentToPut = optCommentToPut.get();
        commentToPut.setPostId(commentPostRequest.getPostId());
        commentToPut.setUsersId(commentPostRequest.getUsersId());
        commentToPut.setText(commentPostRequest.getText());
        return  commentRepositoryImpl.save(commentToPut);

    }

    public void deleteById(Long commentId) {
        commentRepositoryImpl.deleteById(commentId);
    }

    public List<Comment> selectAllByPostId(Long postId,int pageNo,int pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);

        return commentRepositoryImpl.findAllByPostId(postId,pageable);
    }
}
