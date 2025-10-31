package lk.StudyReview.study_review.model;

import jakarta.persistence.*;
import lk.StudyReview.study_review.utils.enums.ReviewStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id",name = "from_user_id")
    private User fromUser;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id",name = "to_user_id")
    private User toUser;
    @Column(name = "message")
    private String message;
    @Column(name="date")
    private Date date;
    @Column(name="is_read")
    private Boolean isRead;

}