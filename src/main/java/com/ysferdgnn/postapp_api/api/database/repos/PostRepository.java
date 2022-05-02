package com.ysferdgnn.postapp_api.api.database.repos;

import com.ysferdgnn.postapp_api.api.database.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
