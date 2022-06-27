package com.ysferdgnn.postapp_api.api.database.repos.abstracts;

import com.ysferdgnn.postapp_api.api.database.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Long> {
    Users findByUsername(String userName);
}
