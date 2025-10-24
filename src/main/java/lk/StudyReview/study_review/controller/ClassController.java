package lk.StudyReview.study_review.controller;

import jakarta.validation.constraints.Null;
import lk.StudyReview.study_review.dto.common.APIResponse;
import lk.StudyReview.study_review.dto.common.Auth.UserDetailsDto;
import lk.StudyReview.study_review.dto.common.ClassDetailsDto;
import lk.StudyReview.study_review.model.ClassDetails;
import lk.StudyReview.study_review.service.ClassDetailsService;
import lk.StudyReview.study_review.utils.enums.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/class")
public class ClassController {
    private final ClassDetailsService classDetailsService;

    // Get all classes
    @GetMapping
    public ResponseEntity<APIResponse<List<ClassDetailsDto>>>  getAllClasses() {
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS,classDetailsService.getAllClasses()));
    }

    // Get class by ID
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<ClassDetailsDto>> getClassById(@PathVariable Long id) {
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS,classDetailsService.getClassById(id)));
    }

    // Create new class
    @PostMapping
    public ResponseEntity<APIResponse<Null>> createClass(@RequestBody ClassDetailsDto classDetailsDto) {
        classDetailsService.createClass(classDetailsDto);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS));
    }

    // Update class
    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Null>> updateClass(@PathVariable Long id, @RequestBody ClassDetailsDto classDetailsDto) {
        classDetailsService.updateClass(id,classDetailsDto);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS));
    }

    // Delete class
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable Long id) {
        classDetailsService.deleteClass(id);
        return ResponseEntity.noContent().build();
    }

}
