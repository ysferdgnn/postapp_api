package com.ysferdgnn.postapp_api.api.database.repos;

import com.ysferdgnn.postapp_api.api.database.models.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes,Long> {
}
