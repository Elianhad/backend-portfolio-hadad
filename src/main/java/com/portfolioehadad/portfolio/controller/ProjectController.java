package com.portfolioehadad.portfolio.controller;

import com.portfolioehadad.portfolio.models.Project;
import com.portfolioehadad.portfolio.service.ProjectService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @PostMapping(value = "/project")
    public ResponseEntity<?> createProject(HttpServletRequest req, @RequestBody Project project){
        return projectService.createProject(req, project);
    }
    @GetMapping(value = "/projects")
    public ResponseEntity<?> getAllProjects(){
        return projectService.getAllProjects();
    }
    @PutMapping(value = "/project/editar/{id}")
    public ResponseEntity<?> putProject(HttpServletRequest req, @PathVariable Long id, @RequestBody Project project){
        return projectService.putOneProject(req, id, project);
    };
    @DeleteMapping(value = "/project/del/{id}")
    public ResponseEntity<?> deleteProject(HttpServletRequest req, @PathVariable Long id){
        return  projectService.delProject(req, id);
    }
}
