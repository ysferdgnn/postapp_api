package com.ysferdgnn.postapp_api.api.database.repository;

import com.ysferdgnn.postapp_api.api.database.models.Likes;
import com.ysferdgnn.postapp_api.api.database.models.Post;
import com.ysferdgnn.postapp_api.api.database.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes,Long> {
    Likes findByUsersAndPost(Users users, Post post);
   // Likes findByUsersId(Long usersId);
}
