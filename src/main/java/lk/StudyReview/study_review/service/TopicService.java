package lk.StudyReview.study_review.service;

import lk.StudyReview.study_review.dto.common.TopicDetailsDto;
import lk.StudyReview.study_review.dto.common.TopicResponseDto;
import lk.StudyReview.study_review.model.Topic;

import java.util.List;

public interface TopicService {
    void saveTopic(TopicDetailsDto topicDetailsDto);
    TopicResponseDto getLessonById(Long id);
    List<TopicResponseDto> getAllTopics(Long id);
    void updateTopics(Long id, TopicDetailsDto topicDetailsDto);
    void deleteTopic(Long id);
}
