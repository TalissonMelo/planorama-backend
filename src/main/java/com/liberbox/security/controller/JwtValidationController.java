package com.liberbox.security.controller;


import com.liberbox.security.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtValidationController {

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/validateToken")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String token) {
        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7);
            boolean isValid = jwtUtil.tokenValido(jwtToken);
            return ResponseEntity.ok(null);
        } else {
            return ResponseEntity.status(401).body(null);
        }
    }
}
