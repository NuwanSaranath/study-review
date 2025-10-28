package lk.StudyReview.study_review.repository;

import lk.StudyReview.study_review.model.Schedule;
import lk.StudyReview.study_review.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Integer> {
    Page<Schedule> findScheduleByStudentOrderByScheduledCompletionDateDesc(User student, Pageable pageable);
}
