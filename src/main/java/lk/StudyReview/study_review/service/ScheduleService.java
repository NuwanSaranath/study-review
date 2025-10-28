package lk.StudyReview.study_review.service;

import lk.StudyReview.study_review.dto.response.PageableScheduleResponse;


import java.util.List;

public interface ScheduleService {

    PageableScheduleResponse getAllSchedules(int page, int size, Long studentId);


}