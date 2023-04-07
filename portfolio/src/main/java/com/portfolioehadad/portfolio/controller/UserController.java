package com.portfolioehadad.portfolio.controller;

import com.portfolioehadad.portfolio.models.UserPorfolio;
import com.portfolioehadad.portfolio.service.AuthService;
import com.portfolioehadad.portfolio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AuthService authService;
    @PostMapping(value= "/sign-up")
    public ResponseEntity<String> createAccount(@RequestBody UserPorfolio userPorfolio){
        return authService.createNewUser(userPorfolio);
    }
    @PostMapping(value = "/login")
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody UserPorfolio user) throws Exception {
        return authService.login(user);
    }

}