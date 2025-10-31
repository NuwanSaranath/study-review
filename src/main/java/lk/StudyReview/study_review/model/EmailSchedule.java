package lk.StudyReview.study_review.model;

import jakarta.persistence.*;
import lk.StudyReview.study_review.model.common.BaseEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class EmailSchedule extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "recepient")
    private String to;
    @Column(name = "subject")
    private String subject;
    @Column(name = "text",length = 2000)
    private String text;

    private boolean done;
    private LocalDateTime  sentDate;

}
