package br.com.caiocarvalho.gestao_vagas.utils;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils {
    public static String objectToJSON(Object obj) {

        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateToken(UUID idCompany) {
        Algorithm algorithm = Algorithm.HMAC256("JAVAGAS_@123#");

        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        var token = JWT.create().withIssuer("javagas")
        .withSubject(idCompany.toString())
        .withExpiresAt(expiresIn)
        .withClaim("roles", "COMPANY")
        .sign(algorithm);
        return token;
    }
}
