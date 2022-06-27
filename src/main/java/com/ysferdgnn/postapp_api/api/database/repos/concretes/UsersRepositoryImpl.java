package com.ysferdgnn.postapp_api.api.database.repos.concretes;

import com.ysferdgnn.postapp_api.api.database.models.Users;
import com.ysferdgnn.postapp_api.api.database.repos.abstracts.UsersRepository;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository

public class UsersRepositoryImpl  extends BaseRepositoryImpl<Users,Long> implements UsersRepository{


    public UsersRepositoryImpl( EntityManager em) {
        super(Users.class, em);
    }

    public Users findByUsername(String username){
       return  entityManager.createQuery("select a from Users a where a.username='"+username+"'",Users.class).getSingleResult();
    }


}
