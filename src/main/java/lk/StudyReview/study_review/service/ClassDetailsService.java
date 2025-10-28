package lk.StudyReview.study_review.service;

import lk.StudyReview.study_review.dto.request.ClassDetailsDto;

import java.util.List;

public interface ClassDetailsService {
    List<ClassDetailsDto> getAllClasses();

    ClassDetailsDto getClassById(Long id);

    void createClass(ClassDetailsDto classDetailsDto);

    void updateClass(Long id, ClassDetailsDto classDetailsDto);

    void deleteClass(Long id);
}
