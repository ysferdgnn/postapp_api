package com.ysferdgnn.postapp_api.api.controllers;


import com.ysferdgnn.postapp_api.api.requests.LoginRequest;
import com.ysferdgnn.postapp_api.api.requests.RefreshRequest;
import com.ysferdgnn.postapp_api.api.responses.LoginResponse;
import com.ysferdgnn.postapp_api.api.database.models.RefreshToken;
import com.ysferdgnn.postapp_api.api.database.models.Users;
import com.ysferdgnn.postapp_api.api.database.services.RefreshTokenService;
import com.ysferdgnn.postapp_api.api.database.services.UsersService;
import com.ysferdgnn.postapp_api.api.security.tokenProviders.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private UsersService usersService;
    private PasswordEncoder passwordEncoder;
    private RefreshTokenService refreshTokenService;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,UsersService usersService,PasswordEncoder passwordEncoder,
                          RefreshTokenService refreshTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.usersService=usersService;
        this.passwordEncoder=passwordEncoder;
        this.refreshTokenService=refreshTokenService;
    }

    @PostMapping("/login")
    public LoginResponse login (@RequestBody LoginRequest loginRequest){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        Users users = usersService.findByUsername(loginRequest.getUsername());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = "Bearer "+jwtTokenProvider.generateJwtToken(authentication);
        String refreshToken =jwtTokenProvider.generateJwtTokenByUserId(users.getId()).getToken();

        return new LoginResponse(jwtToken,users.getId(),refreshToken);

    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody LoginRequest registerRequest){
       Users users= usersService.findByUsername(registerRequest.getUsername());
       if (users != null)
       {
           return  new ResponseEntity<String>("Username already taken", HttpStatus.BAD_REQUEST);
       }
       Users usersToSave = new Users();
        usersToSave.setUsername(registerRequest.getUsername());
        usersToSave.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
       usersService.saveOneUsers(usersToSave);
       return new ResponseEntity<String>("User successfully registered",HttpStatus.OK);
    }
    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refresh(@RequestBody RefreshRequest refreshRequest){

        Optional<RefreshToken> refreshToken = refreshTokenService.findByUserId(refreshRequest.getUsersId());

        if(refreshToken.isEmpty()){
            return  new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Users users = refreshToken.get().getUsers();
        if (!refreshToken.get().getToken().equals(refreshRequest.getRefreshToken()) || refreshTokenService.isRefreshTokenExpired(refreshToken.get())) {
            return  new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }



        String jwtToken = "Bearer "+jwtTokenProvider.generateJwtTokenByUsersId(users.getId());
        System.out.println("Token yenilendi!"+ new Date().toString());
        return new ResponseEntity<LoginResponse>( new LoginResponse(jwtToken,users.getId(),refreshToken.get().getToken()),HttpStatus.OK);
        }






}
