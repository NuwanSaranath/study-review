package lk.StudyReview.study_review.dto.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicDetailsDto {
    @JsonProperty(value = "id",required = false)
    @Positive(message = "id must be a positive value")
    private Long id;

    @JsonProperty("title")
    @NotBlank(message = "title is required")
    @Size(max = 150, message = "title must be at most 150 characters")
    private String title;

    @JsonProperty("class_id")
    @NotNull(message = "class_id is required")
    @Positive(message = "class_id must be a positive value")
    private Long classId;

    @JsonProperty("class_name")
    @Size(max = 150, message = "class_name must be at most 150 characters")
    private String className;
}
