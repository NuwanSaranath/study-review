package lk.StudyReview.study_review.service.impl;

import lk.StudyReview.study_review.dto.request.ScheduleDto;
import lk.StudyReview.study_review.dto.response.PageableScheduleResponse;
import lk.StudyReview.study_review.exception.CommonException;
import lk.StudyReview.study_review.model.Schedule;
import lk.StudyReview.study_review.model.User;
import lk.StudyReview.study_review.repository.ScheduleRepository;
import lk.StudyReview.study_review.repository.UserRepository;
import lk.StudyReview.study_review.service.ScheduleService;
import lk.StudyReview.study_review.utils.convertor.ScheduleConverter;
import lk.StudyReview.study_review.utils.enums.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Override
    public PageableScheduleResponse getAllSchedules(int page, int size,Long studentId) {
        Optional<User> studentOptional = userRepository.findById(studentId);
        if(studentOptional.isEmpty()){
            log.error("Invalid topic id");
            throw new CommonException(ResponseCode.INVALID_REQUEST);
        }

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Schedule> schedulePage = scheduleRepository.findScheduleByStudentOrderByScheduledCompletionDateDesc(studentOptional.get(),pageRequest);
        PageableScheduleResponse pageableScheduleResponse = new PageableScheduleResponse();
        ArrayList<ScheduleDto> scheduleDtos = new ArrayList<>();
        if(!schedulePage.isEmpty()){
            schedulePage.getContent().forEach(schedule -> {
                ScheduleDto scheduleDto = ScheduleConverter.scheduleToScheduleDto(schedule);
                scheduleDtos.add(scheduleDto);
            });
        }
        pageableScheduleResponse.setScheduleDtoList(scheduleDtos);
        pageableScheduleResponse.setTotalPages(schedulePage.getTotalPages());
        pageableScheduleResponse.setCollectionSize(schedulePage.getTotalElements());
        return pageableScheduleResponse;
    }

}
