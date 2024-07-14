package com.challenge.forohub.controller;

import com.challenge.forohub.domain.login.AuthenticationDTO;
import com.challenge.forohub.domain.login.Login;
import com.challenge.forohub.security.JwtResponseDTO;
import com.challenge.forohub.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<JwtResponseDTO> authenticateUser(@RequestBody @Valid AuthenticationDTO authenticationDTO){
        // Make the user's credentials authentication
        Authentication authentication = new UsernamePasswordAuthenticationToken(
            authenticationDTO.username(), authenticationDTO.password());
        // Verify the token
        Authentication authenticated_user = authenticationManager.authenticate(authentication);
        // Get the JWT
        String jwt = tokenService.generateToken((Login) authenticated_user.getPrincipal());
        // Return the JWT and the 200 status code
        return ResponseEntity.ok(new JwtResponseDTO(jwt));
    }
}
