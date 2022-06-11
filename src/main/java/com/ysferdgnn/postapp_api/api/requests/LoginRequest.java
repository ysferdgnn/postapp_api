package com.ysferdgnn.postapp_api.api.requests;

import lombok.Data;

@Data
public class LoginRequest {
    String username;
    String password;
}
