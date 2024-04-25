package com.destiny.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.Map;

public class JWTUtils {
    private static final String KEY = "destiny";

    public static String getToken(Map<String, Object> claims) {
        return JWT.create()
                .withClaim("claim", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 3600 * 12))
                .sign(Algorithm.HMAC256(JWTUtils.KEY));
    }

    public static Map<String, Object> parseToken(String claims) {
        return JWT.require(Algorithm.HMAC256(JWTUtils.KEY))
                .build()
                .verify(claims)
                .getClaim("claim")
                .asMap();
    }
}
