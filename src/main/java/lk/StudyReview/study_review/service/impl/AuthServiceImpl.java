package lk.StudyReview.study_review.service.impl;

import lk.StudyReview.study_review.dto.request.Auth.AuthRequestDto;
import lk.StudyReview.study_review.dto.request.Auth.TokenResponse;
import lk.StudyReview.study_review.dto.request.Auth.UserDetailsDto;
import lk.StudyReview.study_review.exception.CommonException;
//import lk.StudyReview.study_review.model.common.Auth.CustomUserDetails;
import lk.StudyReview.study_review.model.EmailSchedule;
import lk.StudyReview.study_review.model.User;
import lk.StudyReview.study_review.repository.EmailRepository;
import lk.StudyReview.study_review.repository.UserRepository;
import lk.StudyReview.study_review.service.AuthService;
import lk.StudyReview.study_review.utils.enums.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtServiceImpl jwtService;
    private final EmailRepository emailRepository;

    private final EmailService emailService;
    @Autowired
    private TemplateEngine templateEngine;
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
        User savedUser = userRepository.save(user);

        Context context = new Context();
        context.setVariable("firstName", userDetailsDto.getFirstName());
        context.setVariable("lastName", userDetailsDto.getLastName());
        context.setVariable("role", userDetailsDto.getRole());
        context.setVariable("subject", "Welcome to StudyReview!");
        context.setVariable("dashboardLink", "http://studyreview.com/dashboard");

        String htmlContent = templateEngine.process("email-template", context);
        EmailSchedule emailSchedule  = new EmailSchedule();
        emailSchedule.setSubject("Welcome to StudyReview!");
        emailSchedule.setTo(savedUser.getEmail());
        emailSchedule.setSentDate(LocalDateTime.now());
        emailSchedule.setDone(false);
        emailSchedule.setText(htmlContent);
        emailRepository.save(emailSchedule);
    }

    @Override
    public TokenResponse signIn(AuthRequestDto authRequestDto) {
        Optional<User> userOptional = userRepository.findByUserName(authRequestDto.getUserName().trim().toUpperCase());
        if(userOptional.isPresent()){
            User user = userOptional.get();
            if(!passwordEncoder.matches(authRequestDto.getPassword(),user.getPassword())){
                log.error("Invalid user name or password.");
                throw new CommonException(ResponseCode.INVALID_USERNAME_OR_PASSWORD);
            }
            String jwt = jwtService.generateToken(user);
            TokenResponse tokenResponse = new TokenResponse();
            tokenResponse.setTokenType("Bearer");
            log.info("token {}",jwt);
            tokenResponse.setToken(jwt);
            tokenResponse.setExpiresIn(jwtService.getBodyFromToken(jwt).getExpiration());
            return tokenResponse;
        }
        log.error("User is not registered.");
        throw new CommonException(ResponseCode.USER_DOES_NOT_REGISTERED);
    }
}
