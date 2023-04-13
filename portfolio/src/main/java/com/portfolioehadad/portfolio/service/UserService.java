package com.portfolioehadad.portfolio.service;

import com.portfolioehadad.portfolio.models.Profile;
import com.portfolioehadad.portfolio.models.UserPorfolio;
import com.portfolioehadad.portfolio.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class UserService  {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthService authService;
    public ResponseEntity<?> getUserAndProfile(HttpServletRequest req) throws Exception{
        try{
            UserPorfolio user = authService.authChecker(req);
            if (user == null){
                return ResponseEntity.badRequest().body("No puede acceder a la información");
            }
            Profile profile = user.getProfile();
            return new ResponseEntity<>(profile, HttpStatus.OK);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
