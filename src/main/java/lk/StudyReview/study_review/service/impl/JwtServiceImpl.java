package lk.StudyReview.study_review.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lk.StudyReview.study_review.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.stream.Collectors;
@Service
public class JwtServiceImpl implements JwtService {
    @Value("${security.jwt.expiration-ms}")
    private long jwtExpirationTimeInMs;
    @Value("${security.jwt.base64-secret}")
    private String secret;
    private SecretKey key;

    @PostConstruct
    void init(){
        byte[] keyBytes = Decoders.BASE64.decode(Objects.requireNonNull(secret, "Secret must be set in Base64"));
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }
    public String generateToken(UserDetails user){
        Map<String, Object> claims = new HashMap<>();
        claims.put("ROLES", getRoles(user));
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationTimeInMs))
                .signWith(key)
                .compact();
    }

    public String getUserNameFromToken(String token){
        return getBodyFromToken(token).getSubject();
    }
    public Boolean isValidToken(String token,UserDetails userDetails){
        String userNameFromToken = getBodyFromToken(token).getSubject();
        Boolean isExpired = isExpired(token);
        return !isExpired && userDetails.getUsername().equals(userNameFromToken);

    }
    public Boolean isExpired(String token){
        Date expiration = getBodyFromToken(token).getExpiration();
        return expiration.before(new Date());
    }
    public Claims getBodyFromToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private List<String> getRoles(UserDetails userDetails){
        return userDetails
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }
}
