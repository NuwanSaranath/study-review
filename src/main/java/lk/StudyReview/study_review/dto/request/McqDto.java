package lk.StudyReview.study_review.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class McqDto {

    private Long id;
    private Long assignmentId;
    private String question;
    private List<McqOptionDto> options;
    private Long correctAnswerId;
}