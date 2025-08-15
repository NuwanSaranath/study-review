package lk.StudyReview.study_review.service.impl;

import lk.StudyReview.study_review.dto.common.Auth.UserDetailsDto;
import lk.StudyReview.study_review.model.common.User;
import lk.StudyReview.study_review.repository.UserRepository;
import lk.StudyReview.study_review.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    @Override
    public UserDetailsDto getUserDetails(String userName) {
        Optional<User> userOptional = userRepository.findByUserName(userName);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            UserDetailsDto userDetailsDto = new UserDetailsDto();
            userDetailsDto.setUserName(user.getUsername());
            userDetailsDto.setFirstName(user.getFirstName());
            userDetailsDto.setLastName(user.getLastName());
            userDetailsDto.setRole(user.getRole());
            return userDetailsDto;
        }
        return null;
    }
}
