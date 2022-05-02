package com.ysferdgnn.postapp_api.api.database.repos;

import com.ysferdgnn.postapp_api.api.database.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
