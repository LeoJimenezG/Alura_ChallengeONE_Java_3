package com.challenge.forohub.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.challenge.forohub.domain.login.Login;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    // Secret string for the algorithm generation
    private final String apiSecret = "${API_SECRET}";

    public String generateToken(Login user){
        try {
            // Generate the algorithm with the secret
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            // Create the JasonWebToken with additional information
            return JWT.create()
                    .withIssuer("ForumHub")
                    .withSubject(user.getUsername())
                    .withClaim("id", user.getId())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }

    private Instant generateExpirationDate(){
        // Return a datetime plus two hours using UTC time
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-06:00"));
    }

    public String getSubject(String token){
        DecodedJWT decodedJWT = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("ForumHub")
                    .build();

            decodedJWT = verifier.verify(token);
        } catch (JWTVerificationException exception){
            System.out.println(exception.toString());
        }
        if (decodedJWT.getSubject() == null){
            throw new RuntimeException("Verifier invalido");
        }
        return decodedJWT.getSubject();
    }
}
