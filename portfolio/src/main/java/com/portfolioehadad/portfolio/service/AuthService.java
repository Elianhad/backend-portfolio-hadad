package com.portfolioehadad.portfolio.service;

import com.mysql.cj.xdevapi.JsonParser;
import com.portfolioehadad.portfolio.models.JwtModel;
import com.portfolioehadad.portfolio.models.Profile;
import com.portfolioehadad.portfolio.models.UserPorfolio;
import com.portfolioehadad.portfolio.repository.AuthRepository;
import com.portfolioehadad.portfolio.repository.ProfileRepository;
import com.portfolioehadad.portfolio.utils.CheckJwtHeader;
import com.portfolioehadad.portfolio.utils.PasswordEncoderCustom;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AuthService {
    UserPorfolio userloggin;
    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    AuthRepository authRepository;

    @Autowired
    JwtService jwtService;
    @Autowired
    PasswordEncoderCustom passwordEncoded;
    @Autowired
    CheckJwtHeader checkJwtHeader;
    // LOGIN


    public ResponseEntity<?> login(UserPorfolio userPorfolio) throws Exception {
        try {
            try{
            this.userloggin = authRepository.findOneByEmail(userPorfolio.getEmail()).orElseThrow();
            } catch (Exception e){
                throw new Exception("No hay usuarios registrados");
            }
            System.out.print(userPorfolio.getPassword());

            String passLogin = this.userloggin.getPassword();
            if(!passwordEncoded.decode(userPorfolio.getPassword(), passLogin)){
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

    // create new userPorfolio && profile
    public ResponseEntity<?> createNewUser( UserPorfolio userPorfolio) {
        // verify if exist
        if(authRepository.findOneByEmail(userPorfolio.getEmail()).isPresent())
            return ResponseEntity.badRequest().body("El usuario ya existe");
        //  encode password

        userPorfolio.setPassword(passwordEncoded.encode(userPorfolio.getPassword()));
        Profile profile = new Profile();
        profile.setUser(userPorfolio);
        userPorfolio.setProfile(profile);
        try {
            // save userPorfolio
            authRepository.save(userPorfolio);
            profileRepository.save(profile);
        }
        catch(Exception e) {
            return new ResponseEntity<>("Hubo un error en la creación del usuario", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().body("El usuario ha sido creado");
    }
    public UserPorfolio authChecker(HttpServletRequest req) throws Exception{
        try {
            // check header and return email of jwt
            String email= checkJwtHeader.checkHeaderIfAuthorizationTokenAndReturnEmail(req);
            // check if email exist
            return authRepository.findOneByEmail(email).orElseThrow();
        } catch (Exception e){
            throw new Exception("Hubo un error de autenticación");
        }
    }

}
