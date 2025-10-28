package lk.StudyReview.study_review.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import lk.StudyReview.study_review.dto.common.APIResponse;
import lk.StudyReview.study_review.dto.common.AssignmentDetailsDto;
import lk.StudyReview.study_review.dto.common.AssignmentResponseDto;
import lk.StudyReview.study_review.service.AssignmentService;
import lk.StudyReview.study_review.utils.enums.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/assignment")
public class AssignmentControl {
    private final AssignmentService assignmentService;

    @PostMapping
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<APIResponse<Null>> createAssignment(@Valid  @RequestBody AssignmentDetailsDto assignmentDetailsDto) {
        assignmentService.saveAssignment(assignmentDetailsDto);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS));
    }
    @PreAuthorize("hasAnyRole('STUDENT','TEACHER')")
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<AssignmentResponseDto>> getAssignmentById(@PathVariable Long id) {
        AssignmentResponseDto assignmentResponseDto = assignmentService.getAssignmentById(id);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS, assignmentResponseDto));
    }

    @GetMapping("/class/{classId}")
    @PreAuthorize("hasAnyRole('STUDENT','TEACHER')")
    public ResponseEntity<APIResponse<List<AssignmentResponseDto>>> getAllAssignments(@PathVariable Long classId) {
        List<AssignmentResponseDto> allAssignments = assignmentService.getAllAssignments(classId);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS, allAssignments));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<APIResponse<Null>> updateAssignment(@PathVariable Long id,@Valid @RequestBody AssignmentDetailsDto assignmentDetailsDto) {
        assignmentService.updateAssignment(id, assignmentDetailsDto);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS));
    }
    @PreAuthorize("hasRole('TEACHER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Null>> deleteAssignment(@PathVariable Long id) {
        assignmentService.deleteAssignment(id);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS));
    }
}
