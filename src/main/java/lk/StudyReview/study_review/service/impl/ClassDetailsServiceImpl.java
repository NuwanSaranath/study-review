package lk.StudyReview.study_review.service.impl;

import lk.StudyReview.study_review.dto.request.ClassDetailsDto;
import lk.StudyReview.study_review.dto.response.ClassResponseDto;
import lk.StudyReview.study_review.exception.CommonException;
import lk.StudyReview.study_review.model.ClassDetails;
import lk.StudyReview.study_review.model.User;
import lk.StudyReview.study_review.repository.ClassDetailsRepository;
import lk.StudyReview.study_review.repository.UserRepository;
import lk.StudyReview.study_review.service.ClassDetailsService;
import lk.StudyReview.study_review.utils.enums.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
@Slf4j
@Service
@RequiredArgsConstructor
public class ClassDetailsServiceImpl implements ClassDetailsService {
    private final ClassDetailsRepository classDetailsRepository;
    private final UserRepository userRepository;
    @Override
    public List<ClassResponseDto> getAllClasses(Long teacherId) {
        Optional<User> userOptional = userRepository.findById(teacherId);
        if(userOptional.isPresent()) {
            log.error("Invalid user id.");
            throw new CommonException(ResponseCode.INVALID_REQUEST);
        }
        List<ClassDetails> classDetailsList = classDetailsRepository.findAllByTeacher(userOptional.get());

        return classDetailsList
                .stream()
                .map(classDetails -> new ClassResponseDto(
                        classDetails.getId(),
                        classDetails.getClassName(),
                        classDetails.getDescription(),
                        classDetails.getTeacher().getId(),
                        classDetails.getDp(),
                        classDetails.getStudents().size()
                ))
                .collect(Collectors.toList());
    }
    @Override
    public ClassDetailsDto getClassById(Long id) {
        return classDetailsRepository.findById(id)
                .map(classDetails -> new ClassDetailsDto(
                        classDetails.getId(),
                        classDetails.getClassName(),
                        classDetails.getDescription(),
                        classDetails.getTeacher().getId(),
                        classDetails.getDp()
                ))
                .orElseThrow(() -> new CommonException(ResponseCode.CLASS_DOES_NOT_EXIST));
    }
    @Override
    public void createClass(ClassDetailsDto classDetailsDto) {
        Optional<User> teacherOptional = userRepository.findById(classDetailsDto.getId());
        if(teacherOptional.isEmpty()){
            log.error("Invalid teacher.");
            throw new CommonException(ResponseCode.INVALID_REQUEST);
        }
        ClassDetails classDetails = new ClassDetails();
        classDetails.setClassName(classDetailsDto.getClassName());
        classDetails.setDescription(classDetailsDto.getDescription());
        classDetails.setDp(classDetailsDto.getDp());
        classDetails.setTeacher(teacherOptional.get());
        classDetailsRepository.save(classDetails);
    }
    @Override
    public void updateClass(Long id, ClassDetailsDto classDetailsDto) {
        Optional<ClassDetails> classOptional = classDetailsRepository.findById(id);
        if(classOptional.isEmpty()){
            log.error("Invalid class.");
            throw new CommonException(ResponseCode.CLASS_DOES_NOT_EXIST);
        }
        ClassDetails classDetails = classOptional.get();
        if(!classDetails.getTeacher().getId().equals(classDetailsDto.getTeacher())){
            log.error("Invalid request.");
            throw new CommonException(ResponseCode.INVALID_REQUEST);
        }
        if(Objects.nonNull(classDetails.getClassName())){
            classDetails.setClassName(classDetailsDto.getClassName());
        }
        if(Objects.nonNull(classDetails.getDp())){
            classDetails.setDp(classDetailsDto.getDp());
        }
        if(Objects.nonNull(classDetails.getDescription())){
            classDetails.setDescription(classDetailsDto.getDescription());
        }
        classDetailsRepository.save(classDetails);
    }

    @Override
    public void deleteClass(Long id) {
        Optional<ClassDetails> classDetailsOptional = classDetailsRepository.findById(id);
        if(classDetailsOptional.isEmpty()){
            log.error("Invalid class.");
            throw new CommonException(ResponseCode.CLASS_DOES_NOT_EXIST);
        }
        classDetailsRepository.deleteById(id);
    }
}
