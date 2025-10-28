package lk.StudyReview.study_review.repository;

import lk.StudyReview.study_review.model.Assignment;
import lk.StudyReview.study_review.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentRepository  extends JpaRepository<Assignment,Long> {
    List<Assignment> findAllByTopic(Topic topic);

    @Override
    Optional<Assignment> findById(Long aLong);

}
