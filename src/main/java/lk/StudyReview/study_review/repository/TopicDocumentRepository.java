package lk.StudyReview.study_review.repository;

import lk.StudyReview.study_review.model.Topic;
import lk.StudyReview.study_review.model.TopicDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicDocumentRepository extends JpaRepository<TopicDocument,Long> {
    List<TopicDocument> findTopicDocumentByTopic(Topic topic);
}
