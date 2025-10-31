package lk.StudyReview.study_review.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import lk.StudyReview.study_review.dto.common.APIResponse;
import lk.StudyReview.study_review.dto.request.McqDetailsDto;
import lk.StudyReview.study_review.dto.response.McqResponseDto;
import lk.StudyReview.study_review.service.McqService;
import lk.StudyReview.study_review.utils.enums.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mcq")
public class McqController {

    private final McqService mcqService;

    @PostMapping
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<APIResponse<Null>> createMcq(@RequestBody List<@Valid McqDetailsDto> mcqDetailsDtoList) {
        mcqService.saveMcq(mcqDetailsDtoList);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS));
    }

    @GetMapping("/assignment/{assignmentId}")
    @PreAuthorize("hasAnyRole('TEACHER','STUDENT')")
    public ResponseEntity<APIResponse<List<McqResponseDto>>> getAllMcqs(@PathVariable Long assignmentId) {
        List<McqResponseDto> mcqList = mcqService.getAllMcqs(assignmentId);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS, mcqList));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<APIResponse<Null>> deleteMcq(@PathVariable Long id) {
        mcqService.deleteMcq(id);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS));
    }
}
