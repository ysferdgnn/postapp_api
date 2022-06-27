package com.ysferdgnn.postapp_api.api.database.repos.concretes;

import com.ysferdgnn.postapp_api.api.database.models.Likes;
import com.ysferdgnn.postapp_api.api.database.models.Users;
import com.ysferdgnn.postapp_api.api.database.repos.abstracts.LikesRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class LikesRepositoryImpl extends BaseRepositoryImpl<Likes,Long> implements LikesRepository {

    public LikesRepositoryImpl( EntityManager em) {
        super(Likes.class, em);
    }

    public Likes findByUsersIdAndPostId(Long usersId,Long postId){
        return null;
    }
}
