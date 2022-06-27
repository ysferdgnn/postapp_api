package com.ysferdgnn.postapp_api.api.database.repos.abstracts;

import com.ysferdgnn.postapp_api.api.database.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepository extends PagingAndSortingRepository<Post,Long> {
}
