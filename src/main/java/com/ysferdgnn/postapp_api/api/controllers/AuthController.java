package com.ysferdgnn.postapp_api.api.controllers;


import com.ysferdgnn.postapp_api.api.Requests.LoginRequest;
import com.ysferdgnn.postapp_api.api.database.models.Users;
import com.ysferdgnn.postapp_api.api.database.services.UsersService;
import com.ysferdgnn.postapp_api.api.security.tokenProviders.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private UsersService usersService;
    private PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,UsersService usersService,PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.usersService=usersService;
        this.passwordEncoder=passwordEncoder;
    }

    @PostMapping("/login")
    public String login (@RequestBody LoginRequest loginRequest){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = jwtTokenProvider.generateJwtToken(authentication);
        return  "Bearer "+jwtToken;
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
}
