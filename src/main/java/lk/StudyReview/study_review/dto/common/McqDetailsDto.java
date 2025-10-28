package lk.StudyReview.study_review.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class McqDetailsDto {
    private Long assignmentId;
    private String question;
    private List<String> options;
    private String correctAnswer;
}