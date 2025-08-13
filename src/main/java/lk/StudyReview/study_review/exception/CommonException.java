package lk.StudyReview.study_review.exception;

import lk.StudyReview.study_review.utils.enums.ResponseCode;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@Getter
@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class CommonException extends RuntimeException{
    private final ResponseCode responseCode;

    public CommonException(ResponseCode responseCode) {
        super(responseCode.getDescription());
        this.responseCode = responseCode;
    }
    public CommonException(ResponseCode responseCode, Throwable throwable){
        super(responseCode.getDescription(),throwable);
        this.responseCode = responseCode;
    }
    public CommonException(String description){
        super(description);
        this.responseCode = ResponseCode.INVALID_REQUEST;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }
}
