package lk.StudyReview.study_review.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lk.StudyReview.study_review.utils.enums.ResponseCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponse<T> {
    @JsonProperty("timeStamp")
    private Date timeStamp;
    @JsonProperty("status")
    private int status;
    @JsonProperty("message")
    private String message;
    @JsonProperty("body")
    private T body;

    public APIResponse(@NonNull ResponseCode responseCode){
        this.timeStamp = new Date();
        this.status= responseCode.getCode();
        this.message = responseCode.getDescription();
    }
    public APIResponse(@NonNull ResponseCode responseCode, T body){
        this(responseCode);
        this.body = body;
    }
}
