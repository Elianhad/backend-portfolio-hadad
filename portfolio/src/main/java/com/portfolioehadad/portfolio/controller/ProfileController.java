package com.portfolioehadad.portfolio.controller;

import com.portfolioehadad.portfolio.models.Profile;
import com.portfolioehadad.portfolio.service.ProfileService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ProfileController {
    @Autowired
    ProfileService profileService;
    // TODO: get profile desde userController
    @PutMapping("/profile/editar/{id}")
    public ResponseEntity<?> create(HttpServletRequest req, @RequestBody Profile profile, @PathVariable Long id){
        return profileService.editProfile(req, profile, id);
    }

}
