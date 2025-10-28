package lk.StudyReview.study_review.service;

import lk.StudyReview.study_review.dto.request.TopicDetailsDto;
import lk.StudyReview.study_review.dto.response.TopicResponseDto;

import java.util.List;

public interface TopicService {
    void saveTopic(TopicDetailsDto topicDetailsDto);
    TopicResponseDto getLessonById(Long id);
    List<TopicResponseDto> getAllTopics(Long id);
    void updateTopics(Long id, TopicDetailsDto topicDetailsDto);
    void deleteTopic(Long id);
}
