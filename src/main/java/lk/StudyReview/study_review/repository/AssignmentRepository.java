package lk.StudyReview.study_review.repository;

import lk.StudyReview.study_review.model.Assignment;
import lk.StudyReview.study_review.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository  extends JpaRepository<Assignment,Long> {
    List<Assignment> findAllByTopic(Topic topic);
}
