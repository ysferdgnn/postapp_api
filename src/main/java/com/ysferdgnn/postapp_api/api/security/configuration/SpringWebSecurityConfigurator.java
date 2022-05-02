package com.ysferdgnn.postapp_api.api.security.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringWebSecurityConfigurator extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //TODO: enable csrf protection! now it blocks post requests
        http.csrf().disable();

        http.authorizeRequests(
                        authorize ->{
                            authorize.antMatchers("/").permitAll()
                                    .antMatchers("/api/users").permitAll()
                                    .antMatchers("/api/users/*").permitAll()
                                    .antMatchers("/api/post").permitAll()
                                    .antMatchers("/api/post/*").permitAll()
                                    .antMatchers("/api/likes").permitAll()
                                    .antMatchers("/api/likes/*").permitAll()
                                    .antMatchers("/api/comment").permitAll()
                                    .antMatchers("/api/comment/*").permitAll();

                        }
                ).
                authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();

    }
}
