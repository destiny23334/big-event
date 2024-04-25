package com.destiny.big_event;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.netty.handler.ssl.ClientAuth;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    @Test
    public void testGenerate() {
        var claims = new HashMap<String, Object>();
        claims.put("id", 1);
        claims.put("username", "张三");

        String token = JWT.create()
                .withClaim("user", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 3600 * 12))
                .sign(Algorithm.HMAC256("destiny"));
        System.out.println(token);
    }

    @Test
    public void testParse() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                "eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6IuW8oOS4iSJ9LCJleHAiOjE3MTM4MzYzOTh9." +
                "IR70a41xnzOj5psYEsjDrTORRP7-DA8noEI6a1hxmpw";
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("destiny")).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        Map<String, Claim> claims = decodedJWT.getClaims();
        Claim claim = claims.get("user");
        System.out.println(claim);


    }
}
