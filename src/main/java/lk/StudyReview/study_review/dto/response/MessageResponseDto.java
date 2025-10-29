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
    private String currentUserName;
    private Long currentUserId;
    private String partnerUserName;
    private Long partnerUserId;
    private String content;
    private Date createdAt;
}