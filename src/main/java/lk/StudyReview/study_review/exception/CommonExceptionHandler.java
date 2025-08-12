package lk.StudyReview.study_review.exception;

import lk.StudyReview.study_review.dto.common.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler(CommonException.class)
    public ResponseEntity<APIResponse<Void>> handleCommonException(CommonException exception){
        APIResponse<Void> response = new APIResponse<>(exception.getResponseCode());
        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }
}
