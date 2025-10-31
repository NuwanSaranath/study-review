package lk.StudyReview.study_review.controller;

import jakarta.validation.Valid;
import lk.StudyReview.study_review.dto.request.TopicDetailsDto;
import lk.StudyReview.study_review.dto.common.APIResponse;
import lk.StudyReview.study_review.dto.response.TopicResponseDto;
import lk.StudyReview.study_review.service.TopicService;
import lk.StudyReview.study_review.utils.enums.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/topic")
public class TopicController {

    private final TopicService topicService;

    @PostMapping
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<APIResponse<Void>> createTopic(@Valid @RequestBody TopicDetailsDto topicDetailsDto) {
        topicService.saveTopic(topicDetailsDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new APIResponse<>(ResponseCode.SUCCESS));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('TEACHER','STUDENT')")
    public ResponseEntity<APIResponse<TopicResponseDto>> getTopicById(@PathVariable Long id) {
        TopicResponseDto topicResponseDto = topicService.getLessonById(id);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS, topicResponseDto));
    }

    @GetMapping("/class/{classId}")
    @PreAuthorize("hasAnyRole('TEACHER','STUDENT')")
    public ResponseEntity<APIResponse<List<TopicResponseDto>>> getAllTopics(@PathVariable Long classId) {
        List<TopicResponseDto> allTopics = topicService.getAllTopics(classId);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS, allTopics));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<APIResponse<Void>> updateTopic(@PathVariable Long id,
                                                         @Valid @RequestBody TopicDetailsDto topicDetailsDto) {
        topicService.updateTopics(id, topicDetailsDto);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<APIResponse<Void>> deleteTopic(@PathVariable Long id) {
        topicService.deleteTopic(id);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS));
    }
}
