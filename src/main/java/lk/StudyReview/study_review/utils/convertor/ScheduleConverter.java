package lk.StudyReview.study_review.utils.convertor;


import lk.StudyReview.study_review.dto.request.TopicDetailsDto;
import lk.StudyReview.study_review.dto.response.ScheduleDto;
import lk.StudyReview.study_review.model.Schedule;
import lk.StudyReview.study_review.model.Topic;

public class ScheduleConverter {
    public static ScheduleDto scheduleToScheduleDto(Schedule schedule) {
        ScheduleDto scheduleDto = new ScheduleDto();
        scheduleDto.setId(schedule.getId());
        scheduleDto.setScheduledDate(schedule.getScheduledCompletionDate());
        scheduleDto.setFrequency(schedule.getFrequency());
        Topic topic = schedule.getTopic();
        TopicDetailsDto topicDetailsDto = TopicConverter.topicToTopicDto(topic);
        scheduleDto.setTopicDetailsDto(topicDetailsDto);
        return scheduleDto;
    }
}
