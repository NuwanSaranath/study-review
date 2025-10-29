package lk.StudyReview.study_review.utils.convertor;

import lk.StudyReview.study_review.dto.request.TopicDetailsDto;
import lk.StudyReview.study_review.model.Topic;

public class TopicConverter {
    public static TopicDetailsDto topicToTopicDto(Topic topic) {
        TopicDetailsDto topicDetailsDto = new TopicDetailsDto();
        topicDetailsDto.setId(topic.getId());
        topicDetailsDto.setClassId(topic.getClassDetails().getId());
        topicDetailsDto.setTitle(topic.getTitle());
        topicDetailsDto.setClassName(topic.getClassDetails().getClassName());
        return topicDetailsDto;
    }
}
