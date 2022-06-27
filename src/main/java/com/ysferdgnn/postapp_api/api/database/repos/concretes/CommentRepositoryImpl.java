package com.ysferdgnn.postapp_api.api.database.repos.concretes;

import com.ysferdgnn.postapp_api.api.database.models.Comment;
import com.ysferdgnn.postapp_api.api.database.repos.abstracts.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryImpl extends BaseRepositoryImpl<Comment,Long> implements CommentRepository{

    public CommentRepositoryImpl(EntityManager entityManager){
        super(Comment.class,entityManager);
    }

    public List<Comment> findAllByPostId(Long postId,Pageable pageable){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Comment> criteriaQuery =criteriaBuilder.createQuery(Comment.class);
        Root<Comment> root =  criteriaQuery.from(Comment.class);

        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("postId"), postId));
       return entityManager.createQuery(criteriaQuery).setMaxResults(pageable.getPageSize()).setFirstResult((int)pageable.getOffset()).getResultList();
    }

    @Override
    @Transactional
    public <S extends Comment> S save(S entity) {
        entityManager.persist(entity);
        entityManager.refresh(entity);
        return  entity;

    }
}
