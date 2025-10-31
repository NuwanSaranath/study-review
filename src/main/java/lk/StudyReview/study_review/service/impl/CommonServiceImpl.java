package lk.StudyReview.study_review.service.impl;

import lk.StudyReview.study_review.exception.CommonException;
import lk.StudyReview.study_review.model.ClassDetails;
import lk.StudyReview.study_review.model.Schedule;
import lk.StudyReview.study_review.model.Topic;
import lk.StudyReview.study_review.model.User;
import lk.StudyReview.study_review.repository.ScheduleRepository;
import lk.StudyReview.study_review.service.CommonService;
import lk.StudyReview.study_review.utils.enums.ResponseCode;
import lk.StudyReview.study_review.utils.enums.ReviewStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
@Slf4j
@Service
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService {
    private final ScheduleRepository scheduleRepository;
    @Override
    public  Date getNextReviewDate(Date currentStudyTime, int reviewCount) {
        int daysToAdd;
        switch (reviewCount) {
            case 1:  daysToAdd = 0; break;
            case 2:  daysToAdd = 2; break;
            case 3:  daysToAdd = 4; break;
            case 4:  daysToAdd = 8; break;
            case 5:  daysToAdd = 15; break;
            default:  daysToAdd = 31; break;

        }
        return new Date(currentStudyTime.getTime() + (long) daysToAdd * 24 * 3600 * 1000);
    }
    @Override
    public void scheduleTheTopic(Topic savedTopic){
        ClassDetails classDetails = savedTopic.getClassDetails();
        if(Objects.isNull(classDetails)){
            log.error("Invalid class.");
            throw  new CommonException(ResponseCode.INVALID_REQUEST);
        }
        List<User> students = classDetails.getStudents();
        if(!students.isEmpty()){
            students.forEach(student -> {
                for (int i = 0; i < 3; i++) {
                    Schedule schedule = new Schedule();
                    schedule.setStudent(student);
                    schedule.setTopic(savedTopic);
                    schedule.setFrequency(i+1);
                    schedule.setScheduledCompletionDate(getNextReviewDate(new Date(),i+1));
                    schedule.setStatus(ReviewStatus.NOT_COMPLETE);
                    scheduleRepository.save(schedule);
                }
            });
        }
    }
}
