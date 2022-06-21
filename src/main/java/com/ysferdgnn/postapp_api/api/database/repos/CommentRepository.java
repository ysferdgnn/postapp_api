package com.ysferdgnn.postapp_api.api.database.repos;

import com.ysferdgnn.postapp_api.api.database.models.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CommentRepository extends PagingAndSortingRepository<Comment,Long> {
    List<Comment> findAllByPostId(Long postId, Pageable pageable);
}
