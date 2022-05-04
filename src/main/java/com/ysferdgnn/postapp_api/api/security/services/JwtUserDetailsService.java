package com.ysferdgnn.postapp_api.api.security.services;

import com.ysferdgnn.postapp_api.api.database.services.UsersService;
import com.ysferdgnn.postapp_api.api.security.models.JwtUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

  UsersService usersService;

    public JwtUserDetailsService(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return JwtUserDetails.create(usersService.findByUsername(username));
    }

    public UserDetails loadUserById(Long id){
        return JwtUserDetails.create(usersService.findById(id));
    }
}
