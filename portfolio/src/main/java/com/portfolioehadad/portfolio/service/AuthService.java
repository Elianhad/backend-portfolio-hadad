package com.portfolioehadad.portfolio.service;

import com.mysql.cj.xdevapi.JsonParser;
import com.portfolioehadad.portfolio.models.JwtModel;
import com.portfolioehadad.portfolio.models.UserPorfolio;
import com.portfolioehadad.portfolio.repository.AuthRepository;
import com.portfolioehadad.portfolio.utils.PasswordEncoderCustom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthService {

    @Autowired
    AuthRepository authRepository;

    @Autowired
    JwtService jwtService;
    @Autowired
    PasswordEncoderCustom passwordEncoded;
    private JsonParser parser = new JsonParser();
    // LOGIN


    public ResponseEntity<?> login(UserPorfolio userPorfolio) throws Exception {
        try {
            Optional<UserPorfolio> userLogin = authRepository.findOneByEmail(userPorfolio.getEmail());
            if(userLogin.isEmpty()){
                throw new Exception("La cuenta no existe");
            }
            String passLogin = userLogin.get().getPassword();
            if(passwordEncoded.decode(userPorfolio.getPassword(), passLogin)){
                throw new Exception("La contraseña no coincide");
            }
        } catch ( Exception e ){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        String jwt= jwtService.createJwt(userPorfolio.getEmail());
        JwtModel token = new JwtModel();
        token.setToken(jwt);

        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    // create new userPorfolio
    public ResponseEntity<String> createNewUser( UserPorfolio userPorfolio){
        // verify if exist
        if(authRepository.findOneByEmail(userPorfolio.getEmail()).isPresent())
            return ResponseEntity.badRequest().body("El usuario ya existe");
        //  encode password
        userPorfolio.setPassword(passwordEncoded.encode(userPorfolio.getPassword()));
        try {
            // save userPorfolio
            authRepository.save(userPorfolio);
        }
        catch(Exception e) {
            return ResponseEntity.internalServerError().body("Hubo un error en la creación del usuario");
        }
        return ResponseEntity.ok().body("El usuario ha sido creado");
    }

}
