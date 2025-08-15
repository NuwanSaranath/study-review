package lk.StudyReview.study_review.dto.common.Auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestDto {
    @NotBlank(message = "User name is required.")
    @JsonProperty("userName")
    private String userName;
    @NotBlank(message = "Password is required.")
    @JsonProperty("password")
    private String password;
}
