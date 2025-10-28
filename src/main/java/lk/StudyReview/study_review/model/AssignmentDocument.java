package lk.StudyReview.study_review.model;

import jakarta.persistence.*;
import lk.StudyReview.study_review.model.common.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "lesson_document")
public class AssignmentDocument extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @Column(name = "document_id")
    private Document document;
    @ManyToOne
    @JoinColumn(name = "assignment_id",nullable = false,referencedColumnName = "id")
    private Assignment assignment;

}
