package lk.StudyReview.study_review.repository;

import lk.StudyReview.study_review.model.Message;
import lk.StudyReview.study_review.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Long> {

    List<Message> findAllByFromUserOrToUserOrderByDateDesc(User fromUser,User toUser);

    @Query("""
    SELECT DISTINCT 
        CASE 
            WHEN m.fromUser.id = :userId THEN m.toUser 
            ELSE m.fromUser 
        END AS chatUser
    FROM Message m
    WHERE m.fromUser.id = :userId OR m.toUser.id = :userId
    ORDER BY (
        SELECT MAX(m2.date)
        FROM Message m2
        WHERE (m2.fromUser.id = m.fromUser.id AND m2.toUser.id = m.toUser.id)
           OR (m2.fromUser.id = m.toUser.id AND m2.toUser.id = m.fromUser.id)
    ) DESC
""")
    Page<User> findChatUsersByUserId(Long userId, Pageable pageable);


}
