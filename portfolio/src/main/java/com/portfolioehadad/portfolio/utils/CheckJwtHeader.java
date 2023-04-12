package com.portfolioehadad.portfolio.utils;

import com.portfolioehadad.portfolio.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

public class CheckJwtHeader {
    private final String REQ_HEADER = "Authorization";
    private final String REQ_HEADER_PREFIX = "Bearer ";
    @Autowired
    JwtService jwtService;

    public boolean checkHeaderIfAuthorizationToken(HttpServletRequest req, String email) throws Exception{
        String bearerToken = req.getHeader("Authorization");
        if (!bearerToken.startsWith("Bearer ")){
            throw new Exception("No se pudo realizar autenticación");
        }
        String token = bearerToken.replace("Bearer", "");

        return jwtService.getAuthentication(token, email);
    }
}
