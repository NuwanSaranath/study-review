package lk.StudyReview.study_review.controller;

import jakarta.validation.constraints.Null;
import lk.StudyReview.study_review.dto.request.APIResponse;
import lk.StudyReview.study_review.dto.request.ClassDetailsDto;
import lk.StudyReview.study_review.service.ClassDetailsService;
import lk.StudyReview.study_review.utils.enums.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/class")
public class ClassController {
    private final ClassDetailsService classDetailsService;

    @GetMapping
    public ResponseEntity<APIResponse<List<ClassDetailsDto>>>  getAllClasses() {
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS,classDetailsService.getAllClasses()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<ClassDetailsDto>> getClassById(@PathVariable Long id) {
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS,classDetailsService.getClassById(id)));
    }

    @PostMapping
    public ResponseEntity<APIResponse<Null>> createClass(@RequestBody ClassDetailsDto classDetailsDto) {
        classDetailsService.createClass(classDetailsDto);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Null>> updateClass(@PathVariable Long id, @RequestBody ClassDetailsDto classDetailsDto) {
        classDetailsService.updateClass(id,classDetailsDto);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable Long id) {
        classDetailsService.deleteClass(id);
        return ResponseEntity.noContent().build();
    }

}
