package lk.StudyReview.study_review.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lk.StudyReview.study_review.utils.enums.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.stream.Collectors;
@Component
public class JwtTokenUtil {
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
//    public String generateToken(UserDetails user){
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("ROLES", getRoles(user));
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(user.getUsername())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date((new Date()).getTime() + jwtExpirationTimeInMs))
//                .signWith(key)
//                .compact();
//    }
    public String generateToken(Map<String, Object> extraClaims,UserDetails userDetails){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 60 * 10) ) //10h
                .signWith(key,SignatureAlgorithm.HS256)
                .compact();
    }
    public String createToken(UserDetails username, Role role){
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        return generateToken(claims, username);
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
