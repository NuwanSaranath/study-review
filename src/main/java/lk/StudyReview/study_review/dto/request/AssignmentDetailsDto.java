package lk.StudyReview.study_review.dto.request;


import lombok.Getter;
import lombok.Setter;
import java.sql.Time;
import java.util.Date;

@Getter
@Setter
public class AssignmentDetailsDto {
    private String assignmentName;
    private Time timeDuration;
    private Date startTime;
    private Date endTime;
    private Boolean isMcq;
    private Long topicId;
}