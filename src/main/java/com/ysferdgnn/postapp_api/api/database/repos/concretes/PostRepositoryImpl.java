package com.ysferdgnn.postapp_api.api.database.repos.concretes;

import com.ysferdgnn.postapp_api.api.database.models.Post;
import com.ysferdgnn.postapp_api.api.database.repos.abstracts.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class PostRepositoryImpl extends BaseRepositoryImpl<Post,Long> implements PostRepository {

    public PostRepositoryImpl(EntityManager entityManager){
        super(Post.class,entityManager);
    }
}
