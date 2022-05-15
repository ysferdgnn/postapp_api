package com.ysferdgnn.postapp_api.api.Requests;

import lombok.Data;

@Data
public class RefreshRequest {
    private Long usersId;
    private String refreshToken;
}
