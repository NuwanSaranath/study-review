package lk.StudyReview.study_review.model;

import jakarta.persistence.*;
import lk.StudyReview.study_review.model.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "assignment")
public class Assignment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "assignment_name")
    private String assignmentName;
    @Column(name = "time_duration")
    private Time time_duration;
    @Column(name = "start_time")
    private Date startTime;
    @Column(name = "end_time")
    private Date endTime;
    @ManyToOne
    @JoinColumn(name = "lesson_id",nullable = false,referencedColumnName = "id")
    private Topic topic;
    @Column(name = "is_mcq")
    private Boolean isMcq;

}
