package lk.StudyReview.study_review.service.impl;

import lk.StudyReview.study_review.dto.common.Auth.AuthRequestDto;
import lk.StudyReview.study_review.dto.common.Auth.TokenResponse;
import lk.StudyReview.study_review.dto.common.Auth.UserDetailsDto;
import lk.StudyReview.study_review.exception.CommonException;
//import lk.StudyReview.study_review.model.common.Auth.CustomUserDetails;
import lk.StudyReview.study_review.model.common.User;
import lk.StudyReview.study_review.repository.UserRepository;
import lk.StudyReview.study_review.security.JwtTokenUtil;
import lk.StudyReview.study_review.service.AuthService;
import lk.StudyReview.study_review.utils.enums.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;
    @Override
    public void signUp(UserDetailsDto userDetailsDto) {
        Optional<User> userOptional = userRepository.findByUserName(userDetailsDto.getUserName().trim());
        if(userOptional.isPresent()){
            log.error("User name already exist.");
            throw  new CommonException(ResponseCode.USER_ALREADY_REGISTERED);
        }
        User user = new User();
        user.setUserName(userDetailsDto.getUserName().trim().toUpperCase());
        user.setFirstName(userDetailsDto.getFirstName().trim().toUpperCase());
        user.setLastName(userDetailsDto.getLastName().trim().toUpperCase());
        user.setMobileNumber(userDetailsDto.getMobileNumber());
        user.setEmail(userDetailsDto.getEmail().trim().toLowerCase());
        user.setRole(userDetailsDto.getRole());
        user.setPassword(passwordEncoder.encode(userDetailsDto.getPassword()));
        userRepository.save(user);
    }

    @Override
    public TokenResponse signIn(AuthRequestDto authRequestDto) {
        Optional<User> userOptional = userRepository.findByUserName(authRequestDto.getUserName().trim().toUpperCase());
        if(userOptional.isPresent()){
            User user = userOptional.get();
//            CustomUserDetails customUserDetails = new CustomUserDetails(user);
            if(!passwordEncoder.matches(authRequestDto.getPassword(),user.getPassword())){
                log.error("Invalid user name or password.");
                throw new CommonException(ResponseCode.INVALID_USERNAME_OR_PASSWORD);
            }
//            String jwt = jwtTokenUtil.generateToken(customUserDetails);
            TokenResponse tokenResponse = new TokenResponse();
            var jwt = jwtTokenUtil.createToken(user, user.getRole());
            tokenResponse.setTokenType("Bearer");
            tokenResponse.setToken(jwt);
            tokenResponse.setExpiresIn(jwtTokenUtil.getBodyFromToken(jwt).getExpiration());
            return tokenResponse;
        }
        log.error("User is not registered.");
        throw new CommonException(ResponseCode.USER_DOES_NOT_REGISTERED);
    }
}
