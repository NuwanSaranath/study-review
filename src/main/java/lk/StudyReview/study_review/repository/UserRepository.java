package lk.StudyReview.study_review.repository;

import lk.StudyReview.study_review.model.common.User;
import lk.StudyReview.study_review.utils.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserName(String userName);
    Page<User> findAllByRole(Role role, Pageable pageable);
}
