package com.portfolioehadad.portfolio.service;

import com.portfolioehadad.portfolio.models.Experience;
import com.portfolioehadad.portfolio.models.UserPorfolio;
import com.portfolioehadad.portfolio.repository.ExperienceRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceService {
    @Autowired
    ExperienceRepository experienceRepository;
    @Autowired
    AuthService authService;
    public ResponseEntity<?> createExperience(HttpServletRequest req, Experience experience) {
        try{
            UserPorfolio user = authService.authChecker(req);
            if (user == null){
                return new ResponseEntity<>("Hubo un error de autenticación", HttpStatus.BAD_REQUEST);           }
            Experience newExperience = new Experience();
            newExperience.setUser(user);
            newExperience.setTitle(experience.getTitle());
            newExperience.setDescription(experience.getDescription());
            newExperience.setDate(experience.getDate());
            try{
                experienceRepository.save(newExperience);
            } catch (Exception e){
                return new ResponseEntity<>("Hubo un error en la creación del registro", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(newExperience, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<?> getAllExperiences(HttpServletRequest req) {
        try{
            List<Experience> experienceList  = experienceRepository.findAll();

            return new ResponseEntity<>(experienceList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Hubo un error al revisar la base de datos", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<?> putOneExperience(HttpServletRequest req, Long id, Experience experience) {
        try{
            UserPorfolio user = authService.authChecker(req);
            if (user == null){
                return new ResponseEntity<>("Hubo un error de autenticación", HttpStatus.BAD_REQUEST);
            }
            Experience experienceToEdit = experienceRepository.findById(id).orElseThrow();
            System.out.print(experienceToEdit.getUser());
            if(experienceToEdit.getUser() != user){
                return new ResponseEntity<>("No tiene permiso para editar", HttpStatus.BAD_REQUEST);
            }
            experienceToEdit.setTitle(experience.getTitle());
            experienceToEdit.setDescription(experience.getDescription());
            experienceToEdit.setDate(experience.getDate());
            experienceRepository.save(experienceToEdit);
            return new ResponseEntity<>( experienceToEdit, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<?> deleteExperience(HttpServletRequest req, Long id) {
        try{
            UserPorfolio user = authService.authChecker(req);
            if (user == null){
                return new ResponseEntity<>("Hubo un error de autenticación", HttpStatus.BAD_REQUEST);
            }
            Experience experienceToDel = experienceRepository.findById(id).orElseThrow();
            if(experienceToDel.getUser() != user){
                return new ResponseEntity<>("No tiene permiso para editar", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            return new ResponseEntity<>( "Hubo un error en el servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try {
            experienceRepository.deleteById(id);
        } catch (Exception e){
            return new ResponseEntity<>("No se pudo eliminar", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>( "Ha sido eliminado correctamente", HttpStatus.OK);
    }
}
