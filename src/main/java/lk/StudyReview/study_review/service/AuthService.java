package lk.StudyReview.study_review.service;

import lk.StudyReview.study_review.dto.common.Auth.AuthRequestDto;
import lk.StudyReview.study_review.dto.common.Auth.TokenResponse;
import lk.StudyReview.study_review.dto.common.Auth.UserDetailsDto;

public interface AuthService {
    void signUp(UserDetailsDto userDetailsDto);
    TokenResponse signIn(AuthRequestDto authRequestDto);
}
