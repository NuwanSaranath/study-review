package lk.StudyReview.study_review.repository;

import lk.StudyReview.study_review.model.Assignment;
import lk.StudyReview.study_review.model.Mcq;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface McqRepository extends JpaRepository<Mcq,Long> {
    List<Mcq> findAllByAssignment(Assignment assignment);

}
