package com.portfolioehadad.portfolio.controller;

import com.portfolioehadad.portfolio.models.Experience;
import com.portfolioehadad.portfolio.service.ExperienceService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ExperienceController {
    @Autowired
    ExperienceService experienceService;
    @PostMapping(value = "/experiencia")
    public ResponseEntity<?> createExperience(HttpServletRequest req, @RequestBody Experience experience){
        return experienceService.createExperience(req, experience);
    }
    @GetMapping(value ="/experiencia")
    public ResponseEntity<?> getExperiences(HttpServletRequest req){
        return experienceService.getAllExperiences(req);
    }
    @PutMapping(value = "/experiencia/editar/{id]")
    public ResponseEntity<?> putOneExperiencie(HttpServletRequest req, @RequestBody Experience experience, @PathVariable Long id){
        return experienceService.putOneExperience(req, id, experience);
    }
    @DeleteMapping(value = "/experiencia/del/{id]")
    public ResponseEntity<?> deleteOneExperience(HttpServletRequest req, Long id){
        return experienceService.deleteExperience(req, id);
    }
}

