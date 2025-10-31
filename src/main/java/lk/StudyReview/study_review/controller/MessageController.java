package lk.StudyReview.study_review.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import lk.StudyReview.study_review.dto.common.APIResponse;
import lk.StudyReview.study_review.dto.request.MessageDetailsDto;
import lk.StudyReview.study_review.dto.response.MessageResponseDto;
import lk.StudyReview.study_review.dto.response.MessageUserDto;
import lk.StudyReview.study_review.service.MessageService;
import lk.StudyReview.study_review.utils.enums.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/message")
public class MessageController {
    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<APIResponse<Null>> createMessage(@Valid  @RequestBody MessageDetailsDto messageDetailsDto) {
        messageService.sendMessage(messageDetailsDto);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<ArrayList<MessageUserDto>>> getAllMessageUsers(@PathVariable Long id) {
        ArrayList<MessageUserDto> messageResponseDto = messageService.getAllMessageUsers(id);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS, messageResponseDto));
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<MessageResponseDto>>> getAllMessages(@PathVariable Long currentUserId ,@PathVariable Long partnerUserId) {
        List<MessageResponseDto> allMessages = messageService.getAllMessages(currentUserId,partnerUserId);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS, allMessages));
    }

}
