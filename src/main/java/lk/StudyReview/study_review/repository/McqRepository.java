package lk.StudyReview.study_review.repository;

import lk.StudyReview.study_review.model.Assignment;
import lk.StudyReview.study_review.model.Mcq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface McqRepository extends JpaRepository<Mcq,Long> {
    List<Mcq> findAllByAssignment(Assignment assignment);

}
