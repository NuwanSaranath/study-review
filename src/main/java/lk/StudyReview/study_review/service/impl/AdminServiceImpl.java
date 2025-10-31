package lk.StudyReview.study_review.service.impl;

import lk.StudyReview.study_review.dto.common.APIResponse;
import lk.StudyReview.study_review.dto.common.UserDetailsResponseDto;
import lk.StudyReview.study_review.dto.request.Auth.UserDetailsDto;
import lk.StudyReview.study_review.model.EmailSchedule;
import lk.StudyReview.study_review.model.User;
import lk.StudyReview.study_review.repository.EmailRepository;
import lk.StudyReview.study_review.repository.UserRepository;
import lk.StudyReview.study_review.service.AdminService;
import lk.StudyReview.study_review.utils.enums.ResponseCode;
import lk.StudyReview.study_review.utils.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final EmailRepository emailRepository;

    private final EmailService emailService;
    @Autowired
    private TemplateEngine templateEngine;
    @Override
    public Page<UserDetailsResponseDto> getAllUsersByRole(String role, Pageable pageable) {

//        emailService.sendSimpleMail("vihanganirmitha159@gmail.com","Test","Test mail");
        Page<User> userPage = userRepository.findAllByRole(Role.valueOf(role), pageable);
        return userPage.map(this::mapToResponse);




    }

    @Override
    public UserDetailsResponseDto createUser(UserDetailsDto userDetailsDto) {
        User user = new User();
        user.setEmail(userDetailsDto.getEmail());
        user.setPassword(userDetailsDto.getPassword());
        user.setFirstName(userDetailsDto.getFirstName());
        user.setLastName(userDetailsDto.getLastName());
        user.setRole(Role.valueOf(String.valueOf(userDetailsDto.getRole())));
        user.setMobileNumber(String.valueOf(userDetailsDto.getMobileNumber()));
        user.setUserName(userDetailsDto.getUserName());
        User savedUser = userRepository.save(user);



        return mapToResponse(savedUser);
    }

    public UserDetailsResponseDto mapToResponse(User user) {
        UserDetailsResponseDto dto = new UserDetailsResponseDto();
        dto.setUserName(user.getUsername());
        dto.setRole(user.getRole());
        dto.setEmail(user.getEmail());
        dto.setId(user.getId());
        dto.setMobileNumber(user.getMobileNumber());
        dto.setLastName(user.getLastName());
        dto.setFirstName(user.getFirstName());
        dto.setRole(user.getRole());
        return dto;
    }

}
