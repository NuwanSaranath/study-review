package lk.StudyReview.study_review.repository;

import lk.StudyReview.study_review.model.ClassDetails;
import lk.StudyReview.study_review.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic,Long> {
    @Override
    Optional<Topic> findById(Long aLong);


    List<Topic> findAllByClassDetails(ClassDetails classDetails);


}
