package lk.StudyReview.study_review.service;

import lk.StudyReview.study_review.dto.request.Auth.AuthRequestDto;
import lk.StudyReview.study_review.dto.request.Auth.TokenResponse;
import lk.StudyReview.study_review.dto.request.Auth.UserDetailsDto;

public interface AuthService {
    void signUp(UserDetailsDto userDetailsDto);
    TokenResponse signIn(AuthRequestDto authRequestDto);
}
