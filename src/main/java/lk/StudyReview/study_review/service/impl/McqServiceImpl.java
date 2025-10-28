package lk.StudyReview.study_review.service.impl;

import lk.StudyReview.study_review.dto.common.McqDetailsDto;
import lk.StudyReview.study_review.dto.common.McqResponseDto;
import lk.StudyReview.study_review.exception.CommonException;
import lk.StudyReview.study_review.model.Assignment;
import lk.StudyReview.study_review.model.Mcq;
import lk.StudyReview.study_review.model.McqOptions;
import lk.StudyReview.study_review.repository.AssignmentRepository;
import lk.StudyReview.study_review.repository.McqOptionRepository;
import lk.StudyReview.study_review.repository.McqRepository;
import lk.StudyReview.study_review.service.McqService;
import lk.StudyReview.study_review.utils.enums.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class McqServiceImpl implements McqService {
    private final McqRepository mcqRepository;
    private final McqOptionRepository mcqOptionRepository;
    private final AssignmentRepository assignmentRepository;

    @Override
    public void saveMcq(List<McqDetailsDto> mcqDetailsDtoList) {

        if (Objects.isNull(mcqDetailsDtoList) || mcqDetailsDtoList.isEmpty()) {
            log.error("MCQ list cannot be empty.");
            throw new CommonException(ResponseCode.INVALID_REQUEST);
        }

        Long assignmentId = mcqDetailsDtoList.get(0).getAssignmentId();
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("Assignment not found with ID: " + assignmentId));

        for (McqDetailsDto mcqDetailsDto : mcqDetailsDtoList) {
            Mcq mcq = new Mcq();
            mcq.setAssignment(assignment);
            mcq.setQuestion(mcqDetailsDto.getQuestion());
            List<McqOptions> options = mcqDetailsDto.getOptions().stream().map(opt -> {
                McqOptions option = new McqOptions();
                option.setMcq(mcq);
                option.setMcqOption(opt);
                if(opt.equals(mcqDetailsDto.getCorrectAnswer())){
                    mcq.setCorrectAnswer(option);
                }
                return option;
            }).collect(Collectors.toList());
            mcqRepository.save(mcq);
            mcqOptionRepository.saveAll(options);
        }
    }


    @Override
    public List<McqResponseDto> getAllMcqs(Long assignmentId) {
        Optional<Assignment> assignmentOptional = assignmentRepository.findById(assignmentId);
        if (assignmentOptional.isEmpty()){
            log.error("Invalid assignment id");
            throw new CommonException(ResponseCode.INVALID_REQUEST);
        }
        Assignment assignment = assignmentOptional.get();
        List<Mcq> mcqs = mcqRepository.findAllByAssignment(assignmentOptional.get());

        return mcqs.stream().map(mcq -> {
            List<String> options = mcqOptionRepository.findAllByMcq(mcq)
                    .stream()
                    .map(McqOptions::getMcqOption)
                    .collect(Collectors.toList());
            return new McqResponseDto(
                    mcq.getId(),
                    mcq.getQuestion(),
                    assignment.getId(),
                    assignment.getAssignmentName(),
                    options,
                    ""
            );
        }).collect(Collectors.toList());
    }


    @Override
    public void deleteMcq(Long id) {
        Mcq mcq = mcqRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MCQ not found with ID: " + id));
        mcqOptionRepository.deleteByMcq(mcq);
        mcqRepository.delete(mcq);
    }
}
