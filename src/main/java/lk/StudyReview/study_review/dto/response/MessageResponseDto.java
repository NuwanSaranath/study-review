package lk.StudyReview.study_review.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponseDto {

    private Long id;
    private String fromUserName;
    private Long fromUserId;
    private String toUserName;
    private Long toUserId;
    private String content;
    private Date createdAt;
}