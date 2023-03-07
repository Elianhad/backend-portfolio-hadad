package com.portfolioehadad.portfolio.service;

import com.portfolioehadad.portfolio.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthRepository authRepository;
    public boolean login(){
        boolean isUser = false;
        return isUser;
    }
}
