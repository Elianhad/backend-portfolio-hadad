package com.portfolioehadad.portfolio.service;

import com.portfolioehadad.portfolio.models.Education;
import com.portfolioehadad.portfolio.models.UserPorfolio;
import com.portfolioehadad.portfolio.repository.EducationRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService {
    @Autowired
    EducationRepository educationRepository;
    @Autowired
    AuthService authService;

    public ResponseEntity<?> createEducation(HttpServletRequest req, Education education) throws Exception {
        try{
            UserPorfolio user = authService.authChecker(req);
            if (user == null){
                throw new Exception("Hubo un error de autenticación");
            }
            Education newEducation = new Education();
            newEducation.setUser(user);
            newEducation.setName(education.getName());
            newEducation.setCampus(education.getCampus());
            newEducation.setDate(education.getDate());
            System.out.println(newEducation);
            try{
                educationRepository.save(newEducation);
            } catch (Exception e){
                return new ResponseEntity<>("Hubo un error en la creación del registro", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(newEducation, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<?> getAllEducations(HttpServletRequest req) throws Exception {
        try{
            UserPorfolio user = authService.authChecker(req);
            if (user == null){
                throw new Exception("Hubo un error de autenticación");
            }
            List<Education> educationList = user.getEducations();
            return new ResponseEntity<>(educationList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<?> putOneEducation(HttpServletRequest req, Long id, Education education) throws Exception {
        try{
            UserPorfolio user = authService.authChecker(req);
            if (user == null){
                throw new Exception("Hubo un error de autenticación");
            }
            Education educationToEdit = educationRepository.findById(id).orElseThrow();
            System.out.print(educationToEdit.getUser());
            if(educationToEdit.getUser() != user){
                throw new Exception("No tiene permiso para editar");
            }
            educationToEdit.setName(education.getName());
            educationToEdit.setCampus(education.getCampus());
            educationToEdit.setDate(education.getDate());
            educationRepository.save(educationToEdit);
            return new ResponseEntity<>( educationToEdit, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<?> deleteEducation(HttpServletRequest req, Long id) throws Exception {
        try{
            UserPorfolio user = authService.authChecker(req);
            if (user == null){
                throw new Exception("Hubo un error de autenticación");
            }
            Education educationToDel = educationRepository.findById(id).orElseThrow();
            if(educationToDel.getUser() != user){
                throw new Exception("No tiene permiso para editar");
            }
           educationRepository.deleteById(id);
            return new ResponseEntity<>( "Ha sido eliminado correctamente", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
