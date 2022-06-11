package com.ysferdgnn.postapp_api.api.requests;

import lombok.Data;

@Data
public class RefreshRequest {
    private Long usersId;
    private String refreshToken;
}
