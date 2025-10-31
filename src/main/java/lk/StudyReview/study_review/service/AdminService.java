package lk.StudyReview.study_review.service;

import lk.StudyReview.study_review.dto.common.APIResponse;
import lk.StudyReview.study_review.dto.common.Auth.UserDetailsDto;
import lk.StudyReview.study_review.dto.common.UserDetailsResponseDto;
import lk.StudyReview.study_review.utils.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface AdminService {

    Page<UserDetailsResponseDto> getAllUsersByRole(String role, Pageable pageable);
    UserDetailsResponseDto createUser(UserDetailsDto userDetailsDto);
}
