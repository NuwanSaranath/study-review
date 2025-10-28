package lk.StudyReview.study_review.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Data
@Builder
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