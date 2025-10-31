package lk.StudyReview.study_review.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class McqDetailsDto {

    @NotNull(message = "assignmentId is required")
    @Positive(message = "assignmentId must be a positive value")
    private Long assignmentId;

    @NotBlank(message = "question is required")
    @Size(max = 300, message = "question must be at most 300 characters")
    private String question;

    @NotNull(message = "options are required")
    @Size(min = 2, max = 10, message = "options must contain between 2 and 10 items")
    private List<String> options;

    @NotBlank(message = "correctAnswer is required")
    private String correctAnswer;

}