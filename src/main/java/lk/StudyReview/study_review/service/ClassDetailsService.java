package lk.StudyReview.study_review.service;

import lk.StudyReview.study_review.dto.request.ClassDetailsDto;
import lk.StudyReview.study_review.dto.response.ClassResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ClassDetailsService {
    List<ClassResponseDto> getAllClasses(Long userId);

    ClassDetailsDto getClassById(Long id);

    void createClass(String className, String description, Long teacherId, MultipartFile dp);

    void updateClass(Long id, ClassDetailsDto classDetailsDto);

    void deleteClass(Long id);
}
