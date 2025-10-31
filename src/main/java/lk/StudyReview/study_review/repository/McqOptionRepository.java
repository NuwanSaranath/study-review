package lk.StudyReview.study_review.repository;

import lk.StudyReview.study_review.model.Mcq;
import lk.StudyReview.study_review.model.McqOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface McqOptionRepository extends JpaRepository<McqOptions,Long> {

    List<McqOptions> findAllByMcq(Mcq mcq);
    void deleteByMcq(Mcq mcq);
}
