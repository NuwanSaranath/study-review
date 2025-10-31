package lk.StudyReview.study_review.repository;

import lk.StudyReview.study_review.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document,Long> {
}
