package lk.StudyReview.study_review.repository;

import lk.StudyReview.study_review.model.Mcq;
import lk.StudyReview.study_review.model.McqOptions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface McqOptionRepository extends JpaRepository<McqOptions,Long> {

    List<McqOptions> findAllByMcq(Mcq mcq);
}
