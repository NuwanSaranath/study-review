package lk.StudyReview.study_review.repository;

import lk.StudyReview.study_review.model.Assignment;
import lk.StudyReview.study_review.model.AssignmentDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AssignmentDocumentRepository extends JpaRepository<AssignmentDocument,Long> {
    List<AssignmentDocument> findAssignmentDocumentByAssignment(Assignment assignment);
}
