package lk.StudyReview.study_review.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "lesson_document")
public class LessonDocument extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @Column(name = "document_id")
    private Document document;
    @ManyToOne
    @JoinColumn(name = "lesson_id",nullable = false,referencedColumnName = "id")
    private LessonDetails lessonDetails;

}
