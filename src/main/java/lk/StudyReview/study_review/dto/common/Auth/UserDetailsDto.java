package lk.StudyReview.study_review.dto.common.Auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lk.StudyReview.study_review.utils.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDto {
    @JsonProperty("userName")
    @NotBlank(message = "Username is required.")
    @Size(min = 3, max = 30, message = "Username must be 3–30 characters.")
    @Pattern(regexp = "^[A-Za-z0-9_-]+$", message = "Username may contain letters, numbers, underscore, and hyphen only.")
    private String userName;

    @JsonProperty("firstName")
    @NotBlank(message = "First name is required.")
    @Pattern(regexp = "^[A-Za-z]+(?: [A-Za-z]+)*$",message = "Use only English letters with single spaces between names.")
    private String firstName;

    @JsonProperty("lastName")
    @NotBlank(message = "Last name is required.")
    @Pattern(regexp = "^[A-Za-z]+(?: [A-Za-z]+)*$",message = "Use only English letters with single spaces between names.")
    private String lastName;

    @JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY) // never serialize back
    @NotBlank(message = "Password is required.")
    @Size(min = 5, max = 64, message = "Password must be 8–64 characters.")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,64}$",
            message = "Password must have upper, lower, digit and special character.")
    private String password;

    @JsonProperty("email")
    @NotBlank(message = "Email is required.")
    @Email(message = "Email must be valid.")
    private String email;

    @JsonProperty("mobileNumber")
    @Pattern( regexp = "^(?:$|(?:\\+94|0)7\\d{8})$", message = "Invalid mobile number.")
    private String mobileNumber;

    @JsonProperty("role")
    @NotNull(message = "Role is required.")
    private Role role;
}
