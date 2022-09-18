package com.example.demo;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    public String getToken(Member member) {
        String token = null;
        if ("user10".equals(member.getUsername()) && "123".equals(member.getPassword())) {
            Date expireDate = new Date(System.currentTimeMillis() + 30 * 60 * 1000);
            token = Jwts.builder()
                        .setSubject(member.getUsername())
                        .setExpiration(expireDate)
                        .signWith(SignatureAlgorithm.HS512, "secretKey")
                        .compact();
        }
        return token;
    }

    public String access() {
        return "Access successful";
    }
}
