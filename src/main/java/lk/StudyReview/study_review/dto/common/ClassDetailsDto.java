package lk.StudyReview.study_review.dto.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassDetailsDto {

    @JsonProperty("id")
    @Positive(message = "id must be a positive value")
    private Long id;

    @JsonProperty("className")
    @NotBlank(message = "className is required")
    @Size(max = 100, message = "className must be at most 100 characters")
    private String className;

    @JsonProperty("description")
    @Size(max = 500, message = "description must be at most 500 characters")
    private String description;

    @JsonProperty("teacherId")
    @NotNull(message = "teacherId is required")
    @Positive(message = "teacherId must be a positive value")
    private Long teacher;

    @JsonProperty("dp")
    private byte[] dp;
}