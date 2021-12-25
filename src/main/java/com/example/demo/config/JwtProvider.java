package com.example.demo.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;


@Service
public class JwtProvider {
    private final String privateKey = "shfdahdsfkjahfdkjadhflaksjdhfasjdfhalsjdfhskdjfhquoryqoueryiqueyriuqweryasdasdasdasdjaeoijiodhqoifiojaidufaioufoidufaopidfuasoidfuadfoiadufioewruqincqupenriqucirqwneuprqinecuqpeinrcuqewricnqweurcpjhfjbvakjdbfjhqasdyuHAJFJh";




    public String generateToken(Authentication authentication) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();

        Collection<? extends GrantedAuthority> authorities = principal.getAuthorities();
        HashMap<String, String> myClaims = new HashMap<>();

        for (GrantedAuthority authority : authorities) {
            myClaims.put("role", authority.getAuthority());
        }

        return Jwts.builder()
                .setIssuedAt(new Date())
                .setSubject(principal.getUsername())
                .claim("authorities", myClaims)
                .setExpiration(new Date(new Date().getTime() + 3600000))
                .signWith(Keys.hmacShaKeyFor(privateKey.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

    public boolean validateToken(String token) {
        Jwts.parser().setSigningKey(privateKey.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token);
        return true;
    }


    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(privateKey.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
