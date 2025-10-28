package lk.StudyReview.study_review.service;

import lk.StudyReview.study_review.model.Topic;

import java.util.Date;

public interface CommonService {
    Date getNextReviewDate(Date currentStudyTime, int reviewCount);
    void scheduleTheTopic(Topic savedTopic);
}
