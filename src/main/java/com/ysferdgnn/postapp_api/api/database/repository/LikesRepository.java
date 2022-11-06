package com.ysferdgnn.postapp_api.api.database.repository;

import com.ysferdgnn.postapp_api.api.database.models.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes,Long> {
    Likes findByUsersIdAndPostId(Long usersId, Long postId);
}
