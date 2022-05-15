package com.ysferdgnn.postapp_api.api.Responses;

import lombok.Data;

@Data
public class LoginResponse {
    private String message;
    private Long usersId;
    private String refreshToken;

    public LoginResponse(String message, Long usersId, String refreshToken) {
        this.message = message;
        this.usersId = usersId;
        this.refreshToken = refreshToken;
    }
}
