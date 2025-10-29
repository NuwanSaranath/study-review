package lk.StudyReview.study_review.service;

import lk.StudyReview.study_review.dto.request.MessageDetailsDto;
import lk.StudyReview.study_review.dto.response.MessageResponseDto;
import lk.StudyReview.study_review.dto.response.MessageUserDto;

import java.util.ArrayList;
import java.util.List;

public interface MessageService {

    void sendMessage(MessageDetailsDto messageDetailsDto);

    ArrayList<MessageUserDto> getAllMessageUsers(Long id);

    List<MessageResponseDto> getAllMessages(Long currentUserId,Long partnerUserId);


}