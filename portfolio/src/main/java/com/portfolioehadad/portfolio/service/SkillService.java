package com.portfolioehadad.portfolio.service;

import com.portfolioehadad.portfolio.models.Education;
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

    public ResponseEntity<?> createSkill(HttpServletRequest req, Skill skill) throws Exception {
        try{
            UserPorfolio user = authService.authChecker(req);
            if (user == null){
                throw new Exception("Hubo un error de autenticación");
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
                return new ResponseEntity<>("Hubo un error en la creación del registro", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(newSkill, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<?> getAllSkills(HttpServletRequest req) throws Exception {
        try{
            UserPorfolio user = authService.authChecker(req);
            if (user == null){
                throw new Exception("Hubo un error de autenticación");
            }
            List<Skill> skillListList = user.getSkills();
            return new ResponseEntity<>(skillListList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<?> putOneSkill(HttpServletRequest req, Long id, Skill skill) throws Exception {
        try{
            UserPorfolio user = authService.authChecker(req);
            if (user == null){
                throw new Exception("Hubo un error de autenticación");
            }
            Skill skillToEdit = skillRepository.findById(id).orElseThrow();
            System.out.print(skillToEdit.getUser());
            if(skillToEdit.getUser() != user){
                throw new Exception("No tiene permiso para editar");
            }
            skillToEdit.setNameSkill(skill.getNameSkill());
            skillToEdit.setPercentageSkill(skill.getPercentageSkill());
            skillToEdit.setImageSkill(skill.getImageSkill());
            skillRepository.save(skillToEdit);
            return new ResponseEntity<>( skillToEdit, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
