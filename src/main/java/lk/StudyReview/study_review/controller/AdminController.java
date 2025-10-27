package lk.StudyReview.study_review.controller;

import lk.StudyReview.study_review.dto.common.APIResponse;
import lk.StudyReview.study_review.dto.common.Auth.UserDetailsDto;
import lk.StudyReview.study_review.dto.common.UserDetailsResponseDto;
import lk.StudyReview.study_review.service.AdminService;
import lk.StudyReview.study_review.service.UserService;
import lk.StudyReview.study_review.utils.enums.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {
      private AdminService adminService;
//    @GetMapping
//    public ResponseEntity<APIResponse<UserDetailsDto>> getSummery(){
////        UserDetailsDto userDetails = userService.getUserDetails(userName);
//        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS,userDetails));
//
//    }
    @GetMapping
    public ResponseEntity<APIResponse<UserDetailsResponseDto>> getAllUsersByRole(
            @RequestParam String role,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            return adminService.getAllUsersByRole(role, page, size);
        } catch (Exception e) {
            APIResponse<UserDetailsResponseDto> errorResponse =
                    new APIResponse<>(ResponseCode.INTERNAL_ERROR);
            return ResponseEntity.status(ResponseCode.INTERNAL_ERROR.getCode()).body(errorResponse);
        }
    }

}
