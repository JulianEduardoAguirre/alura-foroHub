package com.alura.forohub.infra.security;

import com.alura.forohub.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalUnit;
import java.util.Objects;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;
    public String generarToken(Usuario usuario) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            String token = JWT.create()
                    .withIssuer("ForoHub")
                    .withSubject(usuario.getNombre())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException exception){
            // Invalid Signing configuration / Couldn't convert Claims.
            throw new RuntimeException();
        }
    }

    public String getSubject(String token) {
        if (token == null){
            throw new RuntimeException();
        }

        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            verifier = JWT.require(algorithm)
                    .withIssuer("ForoHub")
                    .build()
                    .verify(token);

            verifier.getSubject();
        } catch (JWTVerificationException exception) {
            // Invalid signature/claims
            System.out.println(exception.toString());
        }

        if (Objects.requireNonNull(verifier).getSubject() == null) {
            throw new RuntimeException("Verifier invalid.");
        }
        return verifier.getSubject();
    }

    private Instant generarFechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
