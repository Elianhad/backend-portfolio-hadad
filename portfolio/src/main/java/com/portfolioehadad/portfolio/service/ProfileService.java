package com.portfolioehadad.portfolio.service;

import com.portfolioehadad.portfolio.models.Profile;
import com.portfolioehadad.portfolio.models.UserPorfolio;
import com.portfolioehadad.portfolio.repository.ProfileRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

@Service
public class ProfileService {
    @Autowired
    AuthService authService;
    @Autowired
    ProfileRepository repository;
    @Autowired
    JwtService jwtService;

    public ResponseEntity<?> editProfile(HttpServletRequest req, Profile profile, Long id) throws HttpStatusCodeException {
        try {
            UserPorfolio user = authService.authChecker(req);
            if (user == null){
                return ResponseEntity.badRequest().body("No puede acceder a la información");
            }
            Profile profile1 = repository.findById(id).orElseThrow();
            profile1.setName(profile.getName());
            profile1.setProfession(profile.getProfession());
            profile1.setImage(profile.getImage());
            repository.save(profile1);
            return new ResponseEntity<>(profile1, HttpStatus.CREATED);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
