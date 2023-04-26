package com.portfolioehadad.portfolio.service;

import com.portfolioehadad.portfolio.models.Skill;
import com.portfolioehadad.portfolio.models.UserPorfolio;
import com.portfolioehadad.portfolio.repository.SkillRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {
    @Autowired
    AuthService authService;
    @Autowired
    SkillRepository skillRepository;

    public ResponseEntity<?> createSkill(HttpServletRequest req, Skill skill) {
        try{
            UserPorfolio user = authService.authChecker(req);
            if (user == null){
                return new ResponseEntity<>("Hubo un error de autenticación", HttpStatus.BAD_REQUEST);
            }
            Skill newSkill = new Skill();
            newSkill.setUser(user);
            newSkill.setNameSkill(skill.getNameSkill());
            newSkill.setImageSkill(skill.getImageSkill());
            newSkill.setPercentageSkill(skill.getPercentageSkill());
            System.out.println(newSkill);
            try{
                skillRepository.save(newSkill);
            } catch (Exception e){
                System.out.println(e.getMessage());
                return new ResponseEntity<>("Hubo un error en la creación del registro", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(newSkill, HttpStatus.CREATED);
        } catch (Exception e){
                return new ResponseEntity<>( "Hubo un error en el servidor", HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<?> getAllSkills(HttpServletRequest req) {
        try{
            List<Skill> skillListList = skillRepository.findAll();
            return new ResponseEntity<>(skillListList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>( "Hubo un error en el servidor", HttpStatus.BAD_GATEWAY);
        }
    }
    public ResponseEntity<?> putOneSkill(HttpServletRequest req, Long id, Skill skill) throws Exception {
        try{
            UserPorfolio user = authService.authChecker(req);
            if (user == null){
                return new ResponseEntity<>("Hubo un error de autenticación", HttpStatus.BAD_REQUEST);
            }
            Skill skillToEdit = skillRepository.findById(id).orElseThrow();
            if(skillToEdit.getUser() != user){
                return new ResponseEntity<>("No tiene permisos para editar", HttpStatus.BAD_REQUEST);
            }
            skillToEdit.setNameSkill(skill.getNameSkill());
            skillToEdit.setPercentageSkill(skill.getPercentageSkill());
            skillToEdit.setImageSkill(skill.getImageSkill());
            skillRepository.save(skillToEdit);
            return new ResponseEntity<>( skillToEdit, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>( "Hubo un error en el servidor", HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<?> delSkill(HttpServletRequest req, Long id) {
        //**
        // Validación de recursos
        // */
        try{
            UserPorfolio user = authService.authChecker(req);
            if (user == null){
                return new ResponseEntity<>("Hubo un error de autenticación", HttpStatus.BAD_REQUEST);
            }
            Skill skillToDel = skillRepository.findById(id).orElseThrow();
            if(skillToDel.getUser() != user){
                return new ResponseEntity<>("No tiene permisos para editar", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        try{
            skillRepository.deleteById(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Hubo un error en servidor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>( "Ha sido eliminado correctamente", HttpStatus.OK);
    }
}
