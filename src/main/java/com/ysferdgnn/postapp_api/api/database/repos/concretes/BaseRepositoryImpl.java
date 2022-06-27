package com.ysferdgnn.postapp_api.api.database.repos.concretes;

import com.ysferdgnn.postapp_api.api.database.repos.abstracts.BaseRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;


public class BaseRepositoryImpl<T,ID extends Serializable> extends SimpleJpaRepository<T,ID> {

    @PersistenceContext
    EntityManager entityManager;

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.entityManager = em;
    }
}
