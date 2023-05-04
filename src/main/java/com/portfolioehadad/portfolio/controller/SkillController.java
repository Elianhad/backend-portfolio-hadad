package com.portfolioehadad.portfolio.controller;

import com.portfolioehadad.portfolio.models.Education;
import com.portfolioehadad.portfolio.models.Skill;
import com.portfolioehadad.portfolio.service.SkillService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class SkillController {
    @Autowired
    SkillService skillService;
    @PostMapping(value = "/skills/create")
    @ResponseBody
    public ResponseEntity<?> createSkill(HttpServletRequest req, @RequestBody Skill skill) throws Exception {
        return skillService.createSkill(req, skill);
    }
    @GetMapping(value = "/skills")
    @ResponseBody
    public  ResponseEntity<?> getAllSkills(HttpServletRequest req) throws Exception {
        return skillService.getAllSkills(req);
    }
    @PutMapping(value = "skills/edit/{id}")
    @ResponseBody
    public ResponseEntity<?> editSkills(HttpServletRequest req, @PathVariable Long id, @RequestBody Skill skill) throws Exception {
        return skillService.putOneSkill(req, id, skill);
    }
    @DeleteMapping(value = "skills/del/{id}")
    public ResponseEntity<?> delSkills(HttpServletRequest req, @PathVariable Long id) throws Exception {
        return skillService.delSkill(req, id);
    }
}
