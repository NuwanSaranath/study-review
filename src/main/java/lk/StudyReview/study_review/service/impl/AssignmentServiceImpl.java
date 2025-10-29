package lk.StudyReview.study_review.service.impl;

import lk.StudyReview.study_review.dto.request.AssignmentDetailsDto;
import lk.StudyReview.study_review.dto.request.AssignmentRequestDto;
import lk.StudyReview.study_review.dto.response.AssignmentResponseDto;
import lk.StudyReview.study_review.dto.response.DocumentDto;
import lk.StudyReview.study_review.dto.response.McqDto;
import lk.StudyReview.study_review.dto.response.McqOptionDto;
import lk.StudyReview.study_review.exception.CommonException;
import lk.StudyReview.study_review.model.*;
import lk.StudyReview.study_review.repository.*;
import lk.StudyReview.study_review.service.AssignmentService;
import lk.StudyReview.study_review.utils.enums.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AssignmentServiceImpl implements AssignmentService {
    private final AssignmentRepository assignmentRepository;
    private final TopicRepository topicRepository;
    private final AssignmentDocumentRepository assignmentDocumentRepository;
    private final McqRepository mcqRepository;
    private final McqOptionRepository mcqOptionRepository;
    private final ClassDetailsRepository classDetailsRepository;

    @Override
    public void saveAssignment(AssignmentDetailsDto assignmentDetailsDto) {
        Optional<Topic> topicOptional = topicRepository.findById(assignmentDetailsDto.getTopicId());
        if (topicOptional.isEmpty()) {
            log.error("Invalid topic id");
            throw new CommonException(ResponseCode.INVALID_REQUEST);
        }

        Assignment assignment = new Assignment();
        assignment.setAssignmentName(assignmentDetailsDto.getAssignmentName());
        assignment.setTime_duration(assignmentDetailsDto.getTimeDuration());
        assignment.setStartTime(assignmentDetailsDto.getStartTime());
        assignment.setEndTime(assignmentDetailsDto.getEndTime());
        assignment.setIsMcq(assignmentDetailsDto.getIsMcq());
        assignment.setTopic(topicOptional.get());
        assignmentRepository.save(assignment);
    }

    @Override
    public AssignmentResponseDto getAssignmentById(Long id) {
        Optional<Assignment> assignmentOptional = assignmentRepository.findById(id);
        if (assignmentOptional.isEmpty()) {
            log.error("Invalid assignment id");
            throw new CommonException(ResponseCode.INVALID_REQUEST);
        }
        Assignment assignment = assignmentOptional.get();
        AssignmentResponseDto assignmentResponseDto = AssignmentResponseDto.builder()
                .id(assignment.getId())
                .assignmentName(assignment.getAssignmentName())
                .timeDuration(assignment.getTime_duration())
                .startTime(assignment.getStartTime())
                .endTime(assignment.getEndTime())
                .isMcq(assignment.getIsMcq())
                .topicId(assignment.getTopic() != null ? assignment.getTopic().getId() : null)
                .topicTitle(assignment.getTopic() != null ? assignment.getTopic().getTitle() : null)
                .build();

        List<AssignmentDocument> documents = assignmentDocumentRepository.findAssignmentDocumentByAssignment(assignment);
        List<DocumentDto> documentDtos = new ArrayList<>();
        documents.forEach(ad -> {
            Document doc = ad.getDocument();
            DocumentDto d = new DocumentDto();
            d.setId(doc.getId());
            d.setName(doc.getName());
            documentDtos.add(d);
        });
        assignmentResponseDto.setDocumentDtos(documentDtos);
        List<Mcq> mcqList = mcqRepository.findAllByAssignment(assignment);
        ArrayList<McqDto> mcqDtoArrayList = new ArrayList<>();
        mcqList.forEach(mcq -> {
            List<McqOptions> mcqOptions = mcqOptionRepository.findAllByMcq(mcq);
            List<McqOptionDto> mcqOptionDtoList = mcqOptions.stream().map(mcqOption -> new McqOptionDto(mcqOption.getId(), mcqOption.getMcqOption())).collect(Collectors.toList());
            McqDto mcqDto = new McqDto();
            mcqDto.setAssignmentId(assignment.getId());
            mcqDto.setOptions(mcqOptionDtoList);
            mcqDto.setQuestion(mcq.getQuestion());
            mcqDto.setId(mcqDto.getId());
            mcqDtoArrayList.add(mcqDto);
        });
        assignmentResponseDto.setMcqs(mcqDtoArrayList);
        return assignmentResponseDto;
    }

    @Override
    public List<AssignmentResponseDto> getAllAssignments(Long classId) {
        Optional<ClassDetails> classDetailsOptional = classDetailsRepository.findById(classId);
        if (classDetailsOptional.isEmpty()) {
            log.error("Invalid class id");
            throw new CommonException(ResponseCode.INVALID_REQUEST);
        }
        ClassDetails classDetails = classDetailsOptional.get();
        List<Topic> topicList = topicRepository.findAllByClassDetails(classDetails);
        List<AssignmentResponseDto> dtoList = new ArrayList<>();
        topicList.forEach(topic -> {
            List<Assignment> assignments = assignmentRepository.findAllByTopic(topic);
            assignments.forEach(a -> {
                AssignmentResponseDto dto = AssignmentResponseDto.builder()
                        .id(a.getId())
                        .assignmentName(a.getAssignmentName())
                        .timeDuration(a.getTime_duration())
                        .startTime(a.getStartTime())
                        .endTime(a.getEndTime())
                        .isMcq(a.getIsMcq())
                        .topicId(a.getTopic() != null ? a.getTopic().getId() : null)
                        .topicTitle(a.getTopic() != null ? a.getTopic().getTitle() : null)
                        .build();
                List<AssignmentDocument> docs = assignmentDocumentRepository.findAssignmentDocumentByAssignment(a);
                List<DocumentDto> documentDtos = new ArrayList<>();
                docs.forEach(ad -> {
                    Document doc = ad.getDocument();
                    DocumentDto d = new DocumentDto();
                    d.setId(doc.getId());
                    d.setName(doc.getName());
                    documentDtos.add(d);
                });

                dtoList.add(dto);
            });
        });

        return dtoList;
    }

    @Override
    public void updateAssignment(Long id, AssignmentDetailsDto assignmentDetailsDto) {
        Optional<Assignment> assignmentOptional = assignmentRepository.findById(id);
        if (assignmentOptional.isEmpty()) {
            log.error("Invalid assignment id");
            throw new CommonException(ResponseCode.INVALID_REQUEST);
        }
        Assignment assignment = assignmentOptional.get();
        assignment.setAssignmentName(assignmentDetailsDto.getAssignmentName());
        assignment.setTime_duration(assignmentDetailsDto.getTimeDuration());
        assignment.setStartTime(assignmentDetailsDto.getStartTime());
        assignment.setEndTime(assignmentDetailsDto.getEndTime());
        assignment.setIsMcq(assignmentDetailsDto.getIsMcq());
        assignmentRepository.save(assignment);
    }

    @Override
    public void deleteAssignment(Long id) {
        Optional<Assignment> assignmentOptional = assignmentRepository.findById(id);
        if (assignmentOptional.isEmpty()) {
            log.error("Invalid assignment id");
            throw new CommonException(ResponseCode.INVALID_REQUEST);
        }
        assignmentRepository.delete(assignmentOptional.get());
    }

    @Override
    public void submitAssignment(Long studentId, Long assignmentId, AssignmentRequestDto assignmentRequestDto) {

    }
}
