package lk.StudyReview.study_review.service;

import lk.StudyReview.study_review.dto.common.Auth.UserDetailsDto;

public interface UserService {
    UserDetailsDto getUserDetails(String userName);
}
