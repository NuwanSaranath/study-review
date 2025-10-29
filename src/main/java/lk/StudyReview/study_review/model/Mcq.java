package lk.StudyReview.study_review.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "mcq")
public class Mcq {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;
    @Column(name = "question")
    private String question;
    @OneToOne
    @JoinColumn(name = "correct_answer")
    private McqOptions correctAnswer;


}
