package lk.StudyReview.study_review.service;

import lk.StudyReview.study_review.dto.common.ClassDetailsDto;
import lk.StudyReview.study_review.model.ClassDetails;

import java.util.List;
import java.util.Optional;

public interface ClassDetailsService {
    List<ClassDetailsDto> getAllClasses();

    ClassDetailsDto getClassById(Long id);

    void createClass(ClassDetailsDto classDetailsDto);

    void updateClass(Long id, ClassDetailsDto classDetailsDto);

    void deleteClass(Long id);
}
