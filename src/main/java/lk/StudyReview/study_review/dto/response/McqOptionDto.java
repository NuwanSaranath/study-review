package lk.StudyReview.study_review.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class McqOptionDto {
    private Long id;
    private String option;
}