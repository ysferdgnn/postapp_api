package com.ysferdgnn.postapp_api.api.Requests;

import lombok.Data;

@Data
public class UsersPostRequest {

    private String name;
    private String username;
    private String password;
}
