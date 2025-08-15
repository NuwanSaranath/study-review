package lk.StudyReview.study_review.model.common;

import jakarta.persistence.Column;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

public class BaseEntity {
    @Column(name = "created_timestamp", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdTimestamp;
    @Column(name = "updated_timestamp")
    private Date updatedTimestamp;

}
