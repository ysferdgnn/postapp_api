package com.ysferdgnn.postapp_api.api.responses;

import com.ysferdgnn.postapp_api.api.database.models.Comment;
import lombok.Data;

@Data
public class CommentResponse {

    private String username;
    private String text;

    public CommentResponse (Comment comment){
        this.username = comment.getUsers().getUsername();
        this.text =comment.getText();
    }

}
