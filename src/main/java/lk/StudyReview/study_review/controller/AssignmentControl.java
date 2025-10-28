package lk.StudyReview.study_review.controller;

import jakarta.validation.constraints.Null;
import lk.StudyReview.study_review.dto.request.APIResponse;
import lk.StudyReview.study_review.dto.request.AssignmentDetailsDto;
import lk.StudyReview.study_review.dto.request.AssignmentResponseDto;
import lk.StudyReview.study_review.service.AssignmentService;
import lk.StudyReview.study_review.utils.enums.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/assignment")
public class AssignmentControl {
    private final AssignmentService assignmentService;

    @PostMapping
    public ResponseEntity<APIResponse<Null>> createAssignment(@RequestBody AssignmentDetailsDto assignmentDetailsDto) {
        assignmentService.saveAssignment(assignmentDetailsDto);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<AssignmentResponseDto>> getAssignmentById(@PathVariable Long id) {
        AssignmentResponseDto assignmentResponseDto = assignmentService.getAssignmentById(id);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS, assignmentResponseDto));
    }

    @GetMapping("/class/{classId}")
    public ResponseEntity<APIResponse<List<AssignmentResponseDto>>> getAllAssignments(@PathVariable Long classId) {
        List<AssignmentResponseDto> allAssignments = assignmentService.getAllAssignments(classId);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS, allAssignments));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Null>> updateAssignment(@PathVariable Long id, @RequestBody AssignmentDetailsDto assignmentDetailsDto) {
        assignmentService.updateAssignment(id, assignmentDetailsDto);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Null>> deleteAssignment(@PathVariable Long id) {
        assignmentService.deleteAssignment(id);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS));
    }
}
