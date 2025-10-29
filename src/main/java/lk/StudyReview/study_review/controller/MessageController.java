package lk.StudyReview.study_review.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import lk.StudyReview.study_review.dto.common.APIResponse;
import lk.StudyReview.study_review.dto.request.MessageDetailsDto;
import lk.StudyReview.study_review.dto.response.MessageResponseDto;
import lk.StudyReview.study_review.service.MessageService;
import lk.StudyReview.study_review.utils.enums.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<APIResponse<MessageResponseDto>> getMessageById(@PathVariable Long id) {
        MessageResponseDto messageResponseDto = messageService.getMessageById(id);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS, messageResponseDto));
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<MessageResponseDto>>> getAllMessages(@PathVariable Long myUserId ,@PathVariable Long otherUserId) {
        List<MessageResponseDto> allMessages = messageService.getAllMessages(myUserId,otherUserId);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS, allMessages));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Null>> updateMessage(@PathVariable Long id, @RequestBody MessageDetailsDto messageDetailsDto) {
        messageService.updateMessage(id, messageDetailsDto);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Null>> deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS));
    }
}
