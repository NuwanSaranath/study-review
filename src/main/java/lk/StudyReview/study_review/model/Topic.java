package lk.StudyReview.study_review.model;

import jakarta.persistence.*;
import lk.StudyReview.study_review.model.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "topic")
public class Topic extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @ManyToOne
    @JoinColumn(name = "class_id",nullable = false,referencedColumnName = "id")
    private ClassDetails classDetails;

}
