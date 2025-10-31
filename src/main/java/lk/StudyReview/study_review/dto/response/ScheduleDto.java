package lk.StudyReview.study_review.dto.response;


import lk.StudyReview.study_review.dto.request.Auth.UserDetailsDto;
import lk.StudyReview.study_review.dto.request.TopicDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDto {
    private Long id;
    private UserDetailsDto student;
    private TopicDetailsDto topicDetailsDto;
    private Integer frequency;
    private Date scheduledDate;
}