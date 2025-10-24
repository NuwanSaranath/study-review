package lk.StudyReview.study_review.repository;

import lk.StudyReview.study_review.model.Assignment;
import lk.StudyReview.study_review.model.AssignmentDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentDocumentRepository extends JpaRepository<AssignmentDocument,Long> {
    List<AssignmentDocument> findAssignmentDocumentByAssignment(Assignment assignment);
}
