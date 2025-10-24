package lk.StudyReview.study_review.dto.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassDetailsDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("className")
    private String className;
    @JsonProperty("description")
    private String description;
    @JsonProperty("teacherId")
    private Long teacher;
    @JsonProperty("dp")
    private byte[] dp;
}