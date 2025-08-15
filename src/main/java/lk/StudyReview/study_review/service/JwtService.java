package lk.StudyReview.study_review.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface JwtService {
    String generateToken(UserDetails user);
    String getUserNameFromToken(String token);
    Boolean isValidToken(String token,UserDetails userDetails);
    Boolean isExpired(String token);
    Claims getBodyFromToken(String token);

}
