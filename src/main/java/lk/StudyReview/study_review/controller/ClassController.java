package lk.StudyReview.study_review.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import lk.StudyReview.study_review.dto.common.APIResponse;
import lk.StudyReview.study_review.dto.request.ClassDetailsDto;
import lk.StudyReview.study_review.dto.response.ClassResponseDto;
import lk.StudyReview.study_review.service.ClassDetailsService;
import lk.StudyReview.study_review.utils.enums.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/class")
public class ClassController {
    private final ClassDetailsService classDetailsService;

    @GetMapping
    @PreAuthorize("hasAnyRole('TEACHER','STUDENT')")
    public ResponseEntity<APIResponse<List<ClassResponseDto>>>  getAllClasses(Long userId) {
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS,classDetailsService.getAllClasses(userId)));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('TEACHER','STUDENT')")
    public ResponseEntity<APIResponse<ClassDetailsDto>> getClassById(@PathVariable Long id) {
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS,classDetailsService.getClassById(id)));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<APIResponse<Void>> createClass(
            @RequestParam("className") String className,
            @RequestParam("description") String description,
            @RequestParam("teacherId") Long teacherId,
            @RequestParam(value = "dp", required = false) MultipartFile dp) {

        classDetailsService.createClass(className, description, teacherId, dp);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<APIResponse<Null>> updateClass(@PathVariable Long id,@Valid @RequestBody ClassDetailsDto classDetailsDto) {
        classDetailsService.updateClass(id,classDetailsDto);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<Void> deleteClass(@PathVariable Long id) {
        classDetailsService.deleteClass(id);
        return ResponseEntity.noContent().build();
    }

}
