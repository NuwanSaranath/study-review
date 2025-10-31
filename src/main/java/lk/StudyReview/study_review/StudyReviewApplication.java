package lk.StudyReview.study_review;

import lk.StudyReview.study_review.controller.AuthController;
import lk.StudyReview.study_review.dto.request.Auth.UserDetailsDto;
import lk.StudyReview.study_review.service.AuthService;
import lk.StudyReview.study_review.service.ClassDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@RequiredArgsConstructor
@SpringBootApplication
@EnableScheduling  // Ensure scheduling is enabled

public class StudyReviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyReviewApplication.class, args);
	}
}
