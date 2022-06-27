package com.ysferdgnn.postapp_api.api.responses;

import com.ysferdgnn.postapp_api.api.database.models.Comment;
import lombok.Data;

@Data
public class CommentResponse {

    private String username;
    private String text;

    public CommentResponse() {
    }

    public static CommentResponse createFromComment(Comment comment){
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.text=comment.getText();
        commentResponse.username = comment.getUsers().getUsername();
        return  commentResponse;
    }

}
