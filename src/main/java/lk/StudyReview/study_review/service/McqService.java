package lk.StudyReview.study_review.service;

import lk.StudyReview.study_review.dto.common.McqDetailsDto;
import lk.StudyReview.study_review.dto.common.McqResponseDto;

import java.util.List;

public interface McqService {
    void saveMcq(List<McqDetailsDto> mcqDetailsDtoList);

    List<McqResponseDto> getAllMcqs(Long assignmentId);

    void deleteMcq(Long id);
}
