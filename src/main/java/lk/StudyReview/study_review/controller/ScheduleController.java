package lk.StudyReview.study_review.controller;

import jakarta.validation.constraints.Null;
import lk.StudyReview.study_review.dto.common.APIResponse;
import lk.StudyReview.study_review.dto.common.ScheduleDto;
import lk.StudyReview.study_review.dto.response.PageableScheduleResponse;
import lk.StudyReview.study_review.service.ScheduleService;
import lk.StudyReview.study_review.utils.enums.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping
    public ResponseEntity<APIResponse<PageableScheduleResponse>> getAllSchedules(@RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam("studentId") Long studentId) {
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS, scheduleService.getAllSchedules(page,size,studentId)));
    }

    // Get schedule by ID
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<ScheduleDto>> getScheduleById(@PathVariable Long id) {
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS, scheduleService.getScheduleById(id)));
    }

    // Create new schedule
    @PostMapping
    public ResponseEntity<APIResponse<Null>> createSchedule(@RequestBody ScheduleDto scheduleDto) {
        scheduleService.createSchedule(scheduleDto);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS));
    }

    // Update schedule
    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Null>> updateSchedule(@PathVariable Long id, @RequestBody ScheduleDto scheduleDto) {
        scheduleService.updateSchedule(id, scheduleDto);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS));
    }

    // Delete schedule
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }
}
