package lk.StudyReview.study_review.controller;

import jakarta.validation.Valid;
import lk.StudyReview.study_review.dto.request.Auth.AuthRequestDto;
import lk.StudyReview.study_review.dto.request.Auth.TokenResponse;
import lk.StudyReview.study_review.dto.request.Auth.UserDetailsDto;
import lk.StudyReview.study_review.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
   private final AuthService authService;
    @PostMapping("/sign-up")
    public void signUp(@Valid @RequestBody UserDetailsDto userDetailsDto){
        authService.signUp(userDetailsDto);
    }
    @GetMapping("/sign-in")
    public TokenResponse signIn(@Valid @RequestBody AuthRequestDto authRequestDto){
        return authService.signIn(authRequestDto);
    }

}
