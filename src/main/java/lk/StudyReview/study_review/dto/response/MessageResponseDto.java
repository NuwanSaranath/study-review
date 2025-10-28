package lk.StudyReview.study_review.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponseDto {

    private Long id;
    private String senderName;
    private String receiverName;
    private String content;
    private LocalDateTime createdAt;
}