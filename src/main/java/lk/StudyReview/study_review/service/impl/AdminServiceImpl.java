package lk.StudyReview.study_review.service.impl;

import lk.StudyReview.study_review.dto.common.APIResponse;
import lk.StudyReview.study_review.dto.common.UserDetailsResponseDto;
import lk.StudyReview.study_review.model.common.User;
import lk.StudyReview.study_review.repository.UserRepository;
import lk.StudyReview.study_review.service.AdminService;
import lk.StudyReview.study_review.utils.enums.ResponseCode;
import lk.StudyReview.study_review.utils.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<APIResponse<UserDetailsResponseDto>> getAllUsersByRole(String role, int page, int size) {

        PageRequest pageable = PageRequest.of(page, size);

        Page<User> userPage = userRepository.findAllByRole(Role.valueOf(role), pageable);

        List<UserDetailsResponseDto> userDtos = userPage.getContent()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        PaginatedResponse<UserDetailsResponseDto> paginatedBody = new PaginatedResponse<>(
                userDtos,
                userPage.getTotalElements(),
                userPage.getTotalPages(),
                userPage.getNumber()
        );

        APIResponse<UserDetailsResponseDto> response = new APIResponse<>(ResponseCode.SUCCESS);
        response.setBody((UserDetailsResponseDto) (Object) paginatedBody);


        return ResponseEntity.ok(response);
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

    @lombok.Data
    @lombok.AllArgsConstructor
    static class PaginatedResponse<T> {
        private List<T> content;
        private long totalElements;
        private int totalPages;
        private int currentPage;
    }
}
