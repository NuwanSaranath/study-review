package lk.StudyReview.study_review.model;

import jakarta.persistence.*;
import lk.StudyReview.study_review.utils.enums.ReviewStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "schedule")

public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id",name = "student_id")
    private User student;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id",name = "topic_id")
    private Topic topic;
    @Column(name="frequency")
    private Integer frequency;
    @Column(name = "schedule_completion_date")
    private Date scheduledCompletionDate;
    @Column(name = "review_status")
    @Enumerated(EnumType.STRING)
    private ReviewStatus Status;
}
