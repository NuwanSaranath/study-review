package lk.StudyReview.study_review.repository;

import lk.StudyReview.study_review.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {
}
