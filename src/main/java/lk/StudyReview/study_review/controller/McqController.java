package lk.StudyReview.study_review.controller;

import jakarta.validation.constraints.Null;
import lk.StudyReview.study_review.dto.request.APIResponse;
import lk.StudyReview.study_review.dto.request.McqDetailsDto;
import lk.StudyReview.study_review.dto.request.McqResponseDto;
import lk.StudyReview.study_review.service.McqService;
import lk.StudyReview.study_review.utils.enums.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mcq")
public class McqController {

    private final McqService mcqService;

    @PostMapping
    public ResponseEntity<APIResponse<Null>> createMcq(@RequestBody List<McqDetailsDto> mcqDetailsDtoList) {
        mcqService.saveMcq(mcqDetailsDtoList);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS));
    }

    @GetMapping("/assignment/{assignmentId}")
    public ResponseEntity<APIResponse<List<McqResponseDto>>> getAllMcqs(@PathVariable Long assignmentId) {
        List<McqResponseDto> mcqList = mcqService.getAllMcqs(assignmentId);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS, mcqList));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Null>> deleteMcq(@PathVariable Long id) {
        mcqService.deleteMcq(id);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS));
    }
}
