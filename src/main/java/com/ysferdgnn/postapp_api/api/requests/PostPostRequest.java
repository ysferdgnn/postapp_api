package com.ysferdgnn.postapp_api.api.requests;

import lombok.Data;

@Data
public class PostPostRequest {

    private String title;
    private String text;
    private Long usersId;
}
