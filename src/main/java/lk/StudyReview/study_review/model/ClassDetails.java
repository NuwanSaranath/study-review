package lk.StudyReview.study_review.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;

@Entity
@Getter
@Setter
@Table(name = "class_details")
public class ClassDetails extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "class_name")
    private String className;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "teacher")
    private User teacher;
    @Column(name = "dp")
    private byte[] dp;

}
