package com.revature.RevSpeed.controllers;

import com.revature.RevSpeed.dto.SignUpRequest;
import com.revature.RevSpeed.models.JwtRequest;
import com.revature.RevSpeed.models.JwtResponse;
import com.revature.RevSpeed.models.User;
import com.revature.RevSpeed.security.JWTHelper;
import com.revature.RevSpeed.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserContoller {

    @Autowired
    private UserDetailsService userDetailsService;  // for user details need to be fetch

    @Autowired
    private AuthenticationManager manager;   // for  authenicate

    @Autowired
    private JWTHelper helper;   // for creating jwt

    @Autowired
    private UserService userService;
    @Autowired
    private JWTHelper jwtHelper;

    @GetMapping("/getAllUser")
    public List<User> getUser(){

        return this.userService.getUsers();
    }

    @GetMapping("/getAUser")
    public UserDetails getUserDetails(@RequestHeader("Authorization") String authorizationHeader) {
        String token =extractToken(authorizationHeader);

        if (token != null) {
            String username = jwtHelper.getUsernameFromToken(token);
            System.out.println(username);
            return userService.getUserByEmail(username);
        }

        throw new UsernameNotFoundException("User not found");
    }

    private String extractToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        this.doAuthenticate(request.getEmail(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

    @PostMapping("/create-user")
    public User createUser(@RequestBody SignUpRequest signUpRequest){
         return  (userService.createUser(signUpRequest));
    }


}
