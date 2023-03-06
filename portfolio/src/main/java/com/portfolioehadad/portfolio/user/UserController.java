package com.portfolioehadad.portfolio.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
public class UserController {
    @Autowired
    public userService service;
    @GetMapping("/{id}")
    public ResponseEntity<String> getUser(@PathVariable String id) {
        ResponseEntity<String> itsFine = ResponseEntity.ok(id);
        return itsFine;
    }
    @PostMapping("/api/user")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User newUser = service.createUser(user);
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }
    @PostMapping("/api/user/profile")
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile){
        Profile newProfile = service.createProfile(profile);
        return new ResponseEntity<Profile>(newProfile, HttpStatus.CREATED);
    }

}
