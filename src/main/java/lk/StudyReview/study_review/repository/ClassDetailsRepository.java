package lk.StudyReview.study_review.repository;

import lk.StudyReview.study_review.model.ClassDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassDetailsRepository extends JpaRepository<ClassDetails, Long> {
}
