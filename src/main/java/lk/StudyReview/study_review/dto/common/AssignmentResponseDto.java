package lk.StudyReview.study_review.dto.common;

import lk.StudyReview.study_review.model.Mcq;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class AssignmentResponseDto {
    private Long id;
    private String assignmentName;
    private Time timeDuration;
    private Date startTime;
    private Date endTime;
    private Boolean isMcq;
    private Long topicId;
    private String topicTitle;
    private List<McqDto> mcqs;
    private List<DocumentDto> documentDtos;
}