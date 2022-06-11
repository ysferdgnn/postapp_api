package com.ysferdgnn.postapp_api.api.requests;

import lombok.Data;

@Data
public class LikesPostRequest {
    private Long usersId;
    private Long postId;
}
