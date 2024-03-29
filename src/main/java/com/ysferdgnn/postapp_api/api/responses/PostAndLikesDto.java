package com.ysferdgnn.postapp_api.api.responses;

import com.ysferdgnn.postapp_api.api.database.models.Likes;
import com.ysferdgnn.postapp_api.api.database.models.Post;
import lombok.Data;

import java.util.List;

@Data
public class PostAndLikesDto {
    private Long id;
    private String title;
    private String text;
    private Long usersId;
    private List<Likes> likes;

    public PostAndLikesDto(Post post) {
        this.id=post.getId();
        this.title=post.getTitle();
        this.text=post.getText();
        this.usersId=post.getUsers().getId();
        this.likes=post.getLikes();
    }
}
