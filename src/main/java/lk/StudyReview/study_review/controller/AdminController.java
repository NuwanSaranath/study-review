package lk.StudyReview.study_review.controller;

import lk.StudyReview.study_review.dto.common.APIResponse;
import lk.StudyReview.study_review.dto.common.Auth.UserDetailsDto;
import lk.StudyReview.study_review.dto.common.UserDetailsResponseDto;
import lk.StudyReview.study_review.service.AdminService;
import lk.StudyReview.study_review.service.UserService;
import lk.StudyReview.study_review.utils.enums.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
@CrossOrigin(origins = "*")
public class AdminController {
    @Autowired
      private AdminService adminService;
//    @GetMapping
//    public ResponseEntity<APIResponse<UserDetailsDto>> getSummery(){
////        UserDetailsDto userDetails = userService.getUserDetails(userName);
//        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS,userDetails));
//
//    }
@GetMapping()
public ResponseEntity<APIResponse<Page<UserDetailsResponseDto>>> getAllUsersByRole(
        @RequestParam String role,
        @PageableDefault(size = 10, page = 0, sort = "id") Pageable pageable
) {
    System.out.println("sdsdasdasd");

        Page<UserDetailsResponseDto> users = adminService.getAllUsersByRole(role, pageable);
        APIResponse<Page<UserDetailsResponseDto>> response =
                new APIResponse<>(ResponseCode.SUCCESS, users);
        return ResponseEntity.ok(response);

}

    @PostMapping
    public ResponseEntity<APIResponse<UserDetailsResponseDto>> createUser(UserDetailsDto userDetailsDto) {
        UserDetailsResponseDto responseDto = adminService.createUser(userDetailsDto);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS,responseDto));
    }

}
