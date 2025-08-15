package lk.StudyReview.study_review.controller;

import lk.StudyReview.study_review.dto.common.APIResponse;
import lk.StudyReview.study_review.dto.common.Auth.UserDetailsDto;
import lk.StudyReview.study_review.service.UserService;
import lk.StudyReview.study_review.utils.enums.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    @GetMapping
    public ResponseEntity<APIResponse<UserDetailsDto>> getUserDetails(@RequestParam("userName") String userName){
        UserDetailsDto userDetails = userService.getUserDetails(userName);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS,userDetails));

    }
}
