package com.ysferdgnn.postapp_api.api.Requests;

import lombok.Data;

@Data
public class CommentPostRequest {
    private Long usersId;
    private Long postId;
    private  String text;
}
