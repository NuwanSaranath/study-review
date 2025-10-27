package lk.StudyReview.study_review.service;

import lk.StudyReview.study_review.dto.common.Auth.UserDetailsDto;
import lk.StudyReview.study_review.dto.common.UserDetailsResponseDto;

public interface UserService {
    UserDetailsResponseDto getUserDetails(String userName);
    UserDetailsResponseDto changeUserRole(String userName, String Role);
}
