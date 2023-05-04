package com.portfolioehadad.portfolio.service;

import com.portfolioehadad.portfolio.models.Profile;
import com.portfolioehadad.portfolio.models.UserPorfolio;
import com.portfolioehadad.portfolio.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService  {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthService authService;
    public ResponseEntity<?> getUserAndProfile(){
        try{
            List<UserPorfolio> users = userRepository.findAll();
            return new ResponseEntity<>(users, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Hubo un error", HttpStatus.BAD_GATEWAY);
        }
    }
}
