package lk.StudyReview.study_review.service;

import lk.StudyReview.study_review.dto.request.MessageDetailsDto;
import lk.StudyReview.study_review.dto.response.MessageResponseDto;

import java.util.List;

public interface MessageService {

    void sendMessage(MessageDetailsDto messageDetailsDto);

    MessageResponseDto getMessageById(Long id);

    List<MessageResponseDto> getAllMessages();

    void updateMessage(Long id, MessageDetailsDto messageDetailsDto);

    void deleteMessage(Long id);
}