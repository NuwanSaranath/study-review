package lk.StudyReview.study_review.utils.enums;

import lombok.Getter;

@Getter
public enum ResponseCode {
    INVALID_REQUEST(1000, "Invalid Request");

    private final int code;
    private final String description;
    ResponseCode(int code, String description) {
        this.code = code;
        this.description = description;

    }


}
