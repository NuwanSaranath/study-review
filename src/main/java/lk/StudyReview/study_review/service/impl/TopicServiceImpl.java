package lk.StudyReview.study_review.service.impl;

import jakarta.transaction.Transactional;
import lk.StudyReview.study_review.dto.TopicDetailsDto;
import lk.StudyReview.study_review.dto.request.DocumentDto;
import lk.StudyReview.study_review.dto.request.TopicResponseDto;
import lk.StudyReview.study_review.exception.CommonException;
import lk.StudyReview.study_review.model.*;
import lk.StudyReview.study_review.repository.*;
import lk.StudyReview.study_review.service.CommonService;
import lk.StudyReview.study_review.service.TopicService;
import lk.StudyReview.study_review.utils.enums.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {
    private final TopicRepository topicRepository;
    private final TopicDocumentRepository topicDocumentRepository;
    private final ClassDetailsRepository classDetailsRepository;
    private final AssignmentRepository assignmentRepository;
    private final UserRepository userRepository;
    private final CommonService commonService;
    private final ScheduleRepository scheduleRepository;

    @Override
    @Transactional
    public void saveTopic(TopicDetailsDto topicDetailsDto) {
        Optional<ClassDetails> classDetailsOptional = classDetailsRepository.findById(topicDetailsDto.getClassId());
        if(classDetailsOptional.isEmpty()){
            log.error("Invalid class id");
            throw  new CommonException(ResponseCode.INVALID_REQUEST);
        }
        Topic topic = new Topic();
        topic.setTitle(topicDetailsDto.getTitle());
        topic.setClassDetails(classDetailsOptional.get());
        Topic savedTopic = topicRepository.save(topic);
        commonService.scheduleTheTopic(savedTopic);
    }

    @Override
    public TopicResponseDto getLessonById(Long id) {
        Optional<Topic> topicOptional = topicRepository.findById(id);
        if(topicOptional.isEmpty()){
            log.error("Invalid topic id");
            throw  new CommonException(ResponseCode.INVALID_REQUEST);
        }
        Topic topic = topicOptional.get();
        TopicResponseDto topicResponseDto = new TopicResponseDto();
        topicResponseDto.setId(topic.getId());
        topicResponseDto.setTitle(topic.getTitle());
        topicResponseDto.setClassName(topic.getClassDetails().getClassName());
        topicResponseDto.setClassId(topic.getClassDetails().getId());
        List<TopicDocument> topicDocumentByTopic = topicDocumentRepository.findTopicDocumentByTopic(topic);
        List<DocumentDto> documentDtoList = new ArrayList<>();
        topicDocumentByTopic.forEach(topicDocument -> {
            Document document = topicDocument.getDocument();
            DocumentDto documentDto = new DocumentDto();
            documentDto.setName(document.getName());
            documentDto.setId(document.getId());
            documentDtoList.add(documentDto);
        });
        topicResponseDto.setDocuments(documentDtoList);
        return topicResponseDto;
    }

    @Override
    public List<TopicResponseDto> getAllTopics(Long id) {
        Optional<ClassDetails> classDetailsOptional = classDetailsRepository.findById(id);
        if(classDetailsOptional.isEmpty()){
            log.error("Invalid class id");
            throw  new CommonException(ResponseCode.INVALID_REQUEST);
        }
        ClassDetails classDetails = classDetailsOptional.get();
        List<Topic> allByClassDetails = topicRepository.findAllByClassDetails(classDetails);
        List<TopicResponseDto> topicResponseDtoArrayList = new ArrayList<>();
        if(!allByClassDetails.isEmpty()){
            allByClassDetails.forEach(topic -> {
                TopicResponseDto topicResponseDto = new TopicResponseDto();
                topicResponseDto.setId(topic.getId());
                topicResponseDto.setTitle(topic.getTitle());
                topicResponseDto.setClassName(topic.getClassDetails().getClassName());
                topicResponseDto.setClassId(topic.getClassDetails().getId());
                List<Assignment> assignmentList = assignmentRepository.findAllByTopic(topic);
                topicResponseDto.setNumberOfAssignment((long) assignmentList.size());
                topicResponseDtoArrayList.add(topicResponseDto);
            });
        }
        return topicResponseDtoArrayList;
    }

    @Override
    public void updateTopics(Long id, TopicDetailsDto topicDetailsDto) {
        Optional<Topic> topicOptional = topicRepository.findById(id);
        if(topicOptional.isEmpty()){
            log.error("Invalid topic id");
            throw  new CommonException(ResponseCode.INVALID_REQUEST);
        }
        Topic topic = topicOptional.get();
        topic.setTitle(topicDetailsDto.getTitle());
        topicRepository.save(topic);
    }

    @Override
    public void deleteTopic(Long id) {
        Optional<Topic> topicOptional = topicRepository.findById(id);
        if(topicOptional.isEmpty()){
            log.error("Invalid topic id");
            throw  new CommonException(ResponseCode.INVALID_REQUEST);
        }
        topicRepository.delete(topicOptional.get());
    }
}
