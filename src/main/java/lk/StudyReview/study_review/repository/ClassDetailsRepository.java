package lk.StudyReview.study_review.repository;

import lk.StudyReview.study_review.model.ClassDetails;
import lk.StudyReview.study_review.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassDetailsRepository extends JpaRepository<ClassDetails, Long> {

    List<ClassDetails> findAllByTeacher(User teacher);
}
