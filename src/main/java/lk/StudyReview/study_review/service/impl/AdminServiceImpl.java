package lk.StudyReview.study_review.service.impl;

import lk.StudyReview.study_review.dto.common.APIResponse;
import lk.StudyReview.study_review.dto.common.Auth.UserDetailsDto;
import lk.StudyReview.study_review.dto.common.UserDetailsResponseDto;
import lk.StudyReview.study_review.model.common.User;
import lk.StudyReview.study_review.repository.UserRepository;
import lk.StudyReview.study_review.service.AdminService;
import lk.StudyReview.study_review.utils.enums.ResponseCode;
import lk.StudyReview.study_review.utils.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;

    @Override
    public Page<UserDetailsResponseDto> getAllUsersByRole(String role, Pageable pageable) {
        System.out.println("a");

        Page<User> userPage = userRepository.findAllByRole(Role.valueOf(role), pageable);
        System.out.println("b");
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
