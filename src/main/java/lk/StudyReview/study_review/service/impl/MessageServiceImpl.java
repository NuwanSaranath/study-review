package lk.StudyReview.study_review.service.impl;

import lk.StudyReview.study_review.dto.request.MessageDetailsDto;
import lk.StudyReview.study_review.dto.response.MessageResponseDto;
import lk.StudyReview.study_review.exception.CommonException;
import lk.StudyReview.study_review.model.Message;
import lk.StudyReview.study_review.model.User;
import lk.StudyReview.study_review.repository.MessageRepository;
import lk.StudyReview.study_review.repository.UserRepository;
import lk.StudyReview.study_review.service.MessageService;
import lk.StudyReview.study_review.utils.enums.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
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
        messageRepository.save(message);
    }

    @Override
    public MessageResponseDto getMessageById(Long id) {
        return null;
    }

    @Override
    public List<MessageResponseDto> getAllMessages(Long currentUserId ,Long chatPartnerId) {
        Optional<User> chatPartnerOptional = userRepository.findById(chatPartnerId);
        Optional<User> currentUserOptional  = userRepository.findById(currentUserId);
        if(chatPartnerOptional.isEmpty() || currentUserOptional.isEmpty()){
            log.info("Invalid user id");
            throw new CommonException(ResponseCode.INVALID_REQUEST);
        }
        User partmerUser = chatPartnerOptional.get();
        User currentUser = currentUserOptional.get();
        List<Message> allMessagesForThirdPartyUser = messageRepository.findAllByFromUserOrToUserOrderByDateDesc(partmerUser, currentUser);
        if(allMessagesForThirdPartyUser.isEmpty()){
            log.info("There is no any messages.");
            throw new CommonException(ResponseCode.MESSAGE_DOES_NOT_EXIST);
        }
//        return allMessagesForThirdPartyUser.stream().map(message ->
//                        new MessageResponseDto(message.getId(), message.getFromUser().getUsername(), message.getToUser().getUsername(), message.getMessage(), message.getDate()))
//                .collect(Collectors.toList());
        return null;
    }

    @Override
    public void updateMessage(Long id, MessageDetailsDto messageDetailsDto) {

    }

    @Override
    public void deleteMessage(Long id) {

    }
}
