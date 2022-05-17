package com.ysferdgnn.postapp_api.api.security.configuration;

import com.ysferdgnn.postapp_api.api.security.entrypoins.JwtAuthenticationEntryPoint;
import com.ysferdgnn.postapp_api.api.security.filters.JwtAuthenticationFilter;
import com.ysferdgnn.postapp_api.api.security.services.JwtUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SpringWebSecurityConfigurator extends WebSecurityConfigurerAdapter {

    JwtUserDetailsService jwtUserDetailsService;
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    public SpringWebSecurityConfigurator(JwtUserDetailsService jwtUserDetailsService, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter(){
        return  new JwtAuthenticationFilter();
    }

    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Override
//    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        authenticationManagerBuilder
//                .userDetailsService(jwtUserDetailsService)
//                .passwordEncoder(passwordEncoder())
//                .and()
//                .build();
//    }

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:3000/");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**",config);
        return new CorsFilter(source);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/auth/**")
                .permitAll()
//                .antMatchers("/api/admin/**")
//                .hasAnyAuthority("admin")
//                .antMatchers(HttpMethod.GET,"/api/post/**")
//                .permitAll()
                .anyRequest()
                .authenticated();

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    //    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        //TODO: enable csrf protection! now it blocks post requests
//        http.csrf().disable();
//        http.cors().disable();
//        http.cors(Customizer.withDefaults());
//
//        http.authorizeRequests(
//                        authorize ->{
//                            authorize.antMatchers("/").permitAll()
//                                    .antMatchers("/api/users").permitAll()
//                                    .antMatchers("/api/users/*").permitAll()
//                                    .antMatchers("/api/post").permitAll()
//                                    .antMatchers("/api/post/*").permitAll()
//                                    .antMatchers("/api/likes").permitAll()
//                                    .antMatchers("/api/likes/*").permitAll()
//                                    .antMatchers("/api/comment").permitAll()
//                                    .antMatchers("/api/comment/*").permitAll();
//
//                        }
//                ).
//                authorizeRequests()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin()
//                .and()
//                .httpBasic();
//
//    }

}
