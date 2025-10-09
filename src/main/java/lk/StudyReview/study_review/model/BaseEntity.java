package lk.StudyReview.study_review.model;

import jakarta.persistence.Column;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

public class BaseEntity {
    @Column(name = "created_timestamp", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdTimestamp;
    @Column(name = "updated_timestamp")
    private Date updatedTimestamp;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "updated_by")
    private String updatedBy;

}
