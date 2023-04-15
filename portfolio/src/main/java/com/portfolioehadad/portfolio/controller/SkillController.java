package com.portfolioehadad.portfolio.controller;

import com.portfolioehadad.portfolio.models.Education;
import com.portfolioehadad.portfolio.models.Skill;
import com.portfolioehadad.portfolio.service.SkillService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SkillController {
    @Autowired
    SkillService skillService;
    @PostMapping(value = "/skills/create")
    public ResponseEntity<?> createSkill(HttpServletRequest req, @RequestBody Skill skill) throws Exception {
        return skillService.createSkill(req, skill);
    }
    @GetMapping(value = "/skills")
    public  ResponseEntity<?> getAllSkills(HttpServletRequest req) throws Exception {
        return skillService.getAllSkills(req);
    }
    @PutMapping(value = "skills/edit/{id}")
    public ResponseEntity<?> editSkills(HttpServletRequest req, @PathVariable Long id, @RequestBody Skill skill) throws Exception {
        return skillService.putOneSkill(req, id, skill);
    }
}
