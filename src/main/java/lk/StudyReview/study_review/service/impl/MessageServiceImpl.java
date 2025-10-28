package lk.StudyReview.study_review.service.impl;

import lk.StudyReview.study_review.dto.request.MessageDetailsDto;
import lk.StudyReview.study_review.dto.response.MessageResponseDto;
import lk.StudyReview.study_review.exception.CommonException;
import lk.StudyReview.study_review.model.Message;
import lk.StudyReview.study_review.model.User;
import lk.StudyReview.study_review.repository.UserRepository;
import lk.StudyReview.study_review.service.MessageService;
import lk.StudyReview.study_review.utils.enums.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final UserRepository userRepository;
    @Override
    public void sendMessage(MessageDetailsDto messageDetailsDto) {
        Optional<User> receiverOptional = userRepository.findById(messageDetailsDto.getReceiverId());
        Optional<User> senderOptional = userRepository.findById(messageDetailsDto.getSenderId());
        if(receiverOptional.isEmpty() || senderOptional.isEmpty()){
            log.info("Invalid user id");
            throw new CommonException(ResponseCode.INVALID_REQUEST);
        }
        Message message = new Message();
        message.setDate(new Date());
        message.setFromUser(senderOptional.get());
        message.setToUser(receiverOptional.get());
        message.setMessage(messageDetailsDto.getContent());
//        mess
    }

    @Override
    public MessageResponseDto getMessageById(Long id) {
        return null;
    }

    @Override
    public List<MessageResponseDto> getAllMessages() {
        return null;
    }

    @Override
    public void updateMessage(Long id, MessageDetailsDto messageDetailsDto) {

    }

    @Override
    public void deleteMessage(Long id) {

    }
}
