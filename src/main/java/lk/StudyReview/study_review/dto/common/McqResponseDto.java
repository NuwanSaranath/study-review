package lk.StudyReview.study_review.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class McqResponseDto {
    private Long id;
    private String question;
    private Long assignmentId;
    private String assignmentTitle;
    private List<String> options;
    private String correctAnswer;
}