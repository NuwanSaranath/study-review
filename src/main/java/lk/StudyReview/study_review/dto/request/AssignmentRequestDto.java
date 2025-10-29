package lk.StudyReview.study_review.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentRequestDto {

    @JsonProperty("mcq_id")
    @Positive(message = "Mcq id must be a positive value")
    private Long mcqId;

    @JsonProperty("mcq_answer_id")
    @NotBlank(message = "mcq_answer_id is required")
    private Long mcqAnswerId;

}
