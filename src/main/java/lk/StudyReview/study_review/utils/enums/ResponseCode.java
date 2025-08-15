package lk.StudyReview.study_review.utils.enums;

import lombok.Getter;

@Getter
public enum ResponseCode {
    INVALID_REQUEST(1000, "Invalid Request"),
    USER_ALREADY_REGISTERED(1001, "User already registered"),
    INVALID_USERNAME_OR_PASSWORD(1002, "Invalid user name or password."),
    USER_DOES_NOT_REGISTERED(1003, "User is not registered."),
    SUCCESS(1004, "Success.");

    private final int code;
    private final String description;
    ResponseCode(int code, String description) {
        this.code = code;
        this.description = description;

    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
