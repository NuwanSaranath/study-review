package lk.StudyReview.study_review.controller;

import jakarta.validation.constraints.Null;
import lk.StudyReview.study_review.dto.TopicDetailsDto;
import lk.StudyReview.study_review.dto.request.APIResponse;
import lk.StudyReview.study_review.dto.request.TopicResponseDto;
import lk.StudyReview.study_review.service.TopicService;
import lk.StudyReview.study_review.utils.enums.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/topic")
public class TopicController {
    private final TopicService topicService;

    @PostMapping
    public ResponseEntity<APIResponse<Null>> createTopic(@RequestBody TopicDetailsDto topicDetailsDto) {
        topicService.saveTopic(topicDetailsDto);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<TopicResponseDto>>  getTopicById(@PathVariable Long id) {
        TopicResponseDto topicResponseDto = topicService.getLessonById(id);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS,topicResponseDto));
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<TopicResponseDto>>> getAllTopics(@PathVariable Long id) {
        List<TopicResponseDto> allTopics = topicService.getAllTopics(id);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS,allTopics));
    }


    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Null>> updateTopic(@PathVariable Long id, @RequestBody TopicDetailsDto topicDetailsDto) {
        topicService.updateTopics(id, topicDetailsDto);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Null>> deleteTopic(@PathVariable Long id) {
        topicService.deleteTopic(id);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS));
    }
}
