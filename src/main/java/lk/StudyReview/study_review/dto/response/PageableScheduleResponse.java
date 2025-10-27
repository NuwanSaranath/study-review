package lk.StudyReview.study_review.dto.response;

import lk.StudyReview.study_review.dto.common.ScheduleDto;
import lk.StudyReview.study_review.model.Schedule;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class PageableScheduleResponse {
    private List<ScheduleDto> scheduleDtoList;
    private Integer totalPages;
    private Long pageNumber;
    private Long collectionSize;
}
