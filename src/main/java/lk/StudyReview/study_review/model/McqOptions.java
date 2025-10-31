package lk.StudyReview.study_review.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "mcq_options")
public class McqOptions {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "mcq_id")
    private Mcq mcq;
    @Column(name = "mcq_option")
    private String mcqOption;
}
