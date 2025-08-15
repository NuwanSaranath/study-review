package lk.StudyReview.study_review.dto.common.Auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse {
    @JsonProperty("token")
    private String token;

    @JsonProperty("tokenType")
    private String tokenType;

    @JsonProperty("expiresIn")
    private Date expiresIn;

}
