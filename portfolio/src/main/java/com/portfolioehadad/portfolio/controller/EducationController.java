package com.portfolioehadad.portfolio.controller;

import com.portfolioehadad.portfolio.models.Education;
import com.portfolioehadad.portfolio.service.EducationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@CrossOrigin
public class EducationController {
    @Autowired
    EducationService educationService;
    @PostMapping(value = "/educations/create")
    @ResponseBody
    public ResponseEntity<?> createEducation(HttpServletRequest req, @RequestBody Education education) throws Exception {
        return educationService.createEducation(req, education);
    }
    @GetMapping(value = "/educations")
    @ResponseBody
    public  ResponseEntity<?> getAllEdu(HttpServletRequest req) throws Exception {
        return educationService.getAllEducations(req);
    }
    @PutMapping(value = "educations/edit/{id}")
    @ResponseBody
    public ResponseEntity<?> editEducation(HttpServletRequest req, @PathVariable Long id, @RequestBody Education education) throws Exception {
        return educationService.putOneEducation(req, id, education);
    }
    @DeleteMapping(value = "educations/del/{id}")
    public ResponseEntity<?> delEducation(HttpServletRequest req, @PathVariable Long id ) throws Exception {
        return educationService.deleteEducation(req, id);
    }
}

