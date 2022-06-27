package com.ysferdgnn.postapp_api.api.database.repos.concretes;

import com.ysferdgnn.postapp_api.api.database.models.RefreshToken;
import com.ysferdgnn.postapp_api.api.database.repos.abstracts.RefreshTokenRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class RefreshTokenRepositoryImpl extends BaseRepositoryImpl<RefreshToken,Long> implements RefreshTokenRepository{

    public RefreshTokenRepositoryImpl( EntityManager em) {
        super(RefreshToken.class, em);
    }

    public Optional<RefreshToken> findByUserId(Long usersId){
       TypedQuery<RefreshToken> typedQuery= entityManager.createQuery("select a from RefreshToken a where a.userId="+usersId,RefreshToken.class);
        return Optional.ofNullable(typedQuery.getSingleResult());
    }
}
