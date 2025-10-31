package lk.StudyReview.study_review.dto.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lk.StudyReview.study_review.utils.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsResponseDto {

    private  Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private Role role;
}
