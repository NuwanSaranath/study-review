package lk.StudyReview.study_review.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopicResponseDto {
    private Long id;
    private String title;
    private Long classId;
    private String className;
    private Long numberOfAssignment;
    private List<DocumentDto> documents;

}
