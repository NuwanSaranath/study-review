package lk.StudyReview.study_review.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageUserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Date lastMessageDate;
    private Long userId;
    private Boolean isRead;
    private byte[] profilePic;
}
