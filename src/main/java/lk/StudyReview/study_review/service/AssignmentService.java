package lk.StudyReview.study_review.service;

import lk.StudyReview.study_review.dto.request.AssignmentDetailsDto;
import lk.StudyReview.study_review.dto.response.AssignmentResponseDto;

import java.util.List;

public interface AssignmentService {
    void saveAssignment(AssignmentDetailsDto assignmentDetailsDto);
    AssignmentResponseDto getAssignmentById(Long id);
    List<AssignmentResponseDto> getAllAssignments(Long classId);
    void updateAssignment(Long id, AssignmentDetailsDto assignmentDetailsDto);
    void deleteAssignment(Long id);
}
