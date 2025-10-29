package lk.StudyReview.study_review.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDetailsDto {

    @NotBlank(message = "Sender name is required")
    @Size(max = 100, message = "Sender name cannot exceed 100 characters")
    private String senderName;

    @NotNull(message = "Sender ID is required")
    private Long senderId;

    @NotBlank(message = "Receiver name is required")
    @Size(max = 100, message = "Receiver name cannot exceed 100 characters")
    private String receiverName;

    @NotNull(message = "Receiver ID is required")
    private Long receiverId;

    @NotBlank(message = "Message content cannot be empty")
    @Size(max = 1000, message = "Message content cannot exceed 1000 characters")
    private String content;
}
