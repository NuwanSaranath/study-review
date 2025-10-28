package lk.StudyReview.study_review.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDetailsDto {

    private String senderName;
    private Long senderId;
    private String receiverName;
    private Long receiverId;
    private String content;
}