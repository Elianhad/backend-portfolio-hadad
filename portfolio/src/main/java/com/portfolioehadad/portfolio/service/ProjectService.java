package com.portfolioehadad.portfolio.service;

import com.portfolioehadad.portfolio.models.Project;
import com.portfolioehadad.portfolio.models.UserPorfolio;
import com.portfolioehadad.portfolio.repository.ProjectRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    AuthService authService;
    public ResponseEntity<?> createProject(HttpServletRequest req, Project project){
        try {
            UserPorfolio user = authService.authChecker(req);
            if (user == null) {
                return new ResponseEntity<>("Hubo un error de autenticación", HttpStatus.BAD_REQUEST);
            }
            Project newProject = new Project();
            newProject.setUser(user);
            newProject.setNameProject(project.getNameProject());
            newProject.setDescription(project.getDescription());
            newProject.setImageProject(project.getImageProject());
            newProject.setSkillsProject(project.getSkillsProject());
            newProject.setDateOfDevelop(project.getDateOfDevelop());
            newProject.setLinkTo(project.getLinkTo());
            try{
                projectRepository.save(newProject);
            } catch (Exception e){
                return new ResponseEntity<>("Hubo un error en la creación del proyecto", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(newProject, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>( "Hubo un error en el servidor", HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<?> getAllProjects(){
        try{
            List<Project> projectsList = projectRepository.findAll();
            return new ResponseEntity<>(projectsList, HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity<>("Hubo un error", HttpStatus.BAD_GATEWAY);
        }
    }
    public ResponseEntity<?> putOneProject(HttpServletRequest req, Long id, Project project){
        try {
            UserPorfolio user = authService.authChecker(req);
            if (user == null) {
                return new ResponseEntity<>("Hubo un error de autenticación", HttpStatus.BAD_REQUEST);
            }
            Project projectToEdit = projectRepository.findById(id).orElseThrow();
            if(projectToEdit.getUser() != user){
                return new ResponseEntity<>("No tiene permisos para editar", HttpStatus.BAD_REQUEST);
            }
            projectToEdit.setNameProject(project.getNameProject());
            projectToEdit.setSkillsProject(project.getSkillsProject());
            projectToEdit.setImageProject(project.getImageProject());
            projectToEdit.setDescription(project.getDescription());
            projectToEdit.setLinkTo(project.getLinkTo());
            projectToEdit.setDateOfDevelop(project.getDateOfDevelop());
            try{
                projectRepository.save(projectToEdit);
            } catch (Exception e){
                return new ResponseEntity<>("Hubo un error en la actualización del proyecto", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(projectToEdit, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>( "Hubo un error en el servidor", HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<?> delProject(HttpServletRequest req, Long id){
        try {
            UserPorfolio user = authService.authChecker(req);
            if (user == null) {
                return new ResponseEntity<>("Hubo un error de autenticación", HttpStatus.BAD_REQUEST);
            }
            Project projectToEdit = projectRepository.findById(id).orElseThrow();
            if(projectToEdit.getUser() != user){
                return new ResponseEntity<>("No tiene permisos para editar", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return new ResponseEntity<>( "Hubo un error en el servidor", HttpStatus.BAD_REQUEST);
        }
        try{
            projectRepository.deleteById(id);
        } catch (Exception e){
            return new ResponseEntity<>("Hubo un error en la eliminación del proyecto", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Ha sido eliminado correctamente", HttpStatus.ACCEPTED);
    }

}
