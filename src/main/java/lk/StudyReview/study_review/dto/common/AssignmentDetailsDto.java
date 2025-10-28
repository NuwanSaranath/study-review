package lk.StudyReview.study_review.dto.common;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
public class AssignmentDetailsDto {

    @NotBlank(message = "Assignment name is required")
    @Size(max = 100, message = "Assignment name must be at most 100 characters")
    private String assignmentName;

    @NotNull(message = "Time duration is required")
    private Time timeDuration;

    @NotNull(message = "Start time is required")
    private Date startTime;

    @NotNull(message = "End time is required")
    private Date endTime;

    @NotNull(message = "isMcq is required")
    private Boolean isMcq;

    @NotNull(message = "topicId is required")
    @Positive(message = "topicId must be a positive value")
    private Long topicId;
}
