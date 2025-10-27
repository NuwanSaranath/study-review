package lk.StudyReview.study_review.service.impl;

import lk.StudyReview.study_review.dto.common.Auth.UserDetailsDto;
import lk.StudyReview.study_review.dto.common.UserDetailsResponseDto;
import lk.StudyReview.study_review.model.common.User;
import lk.StudyReview.study_review.repository.UserRepository;
import lk.StudyReview.study_review.service.UserService;
import lk.StudyReview.study_review.utils.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    @Override
    public UserDetailsResponseDto getUserDetails(String userName) {
        Optional<User> userOptional = userRepository.findByUserName(userName);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            UserDetailsResponseDto userDetailsDto = new UserDetailsResponseDto();
            userDetailsDto.setUserName(user.getUsername());
            userDetailsDto.setFirstName(user.getFirstName());
            userDetailsDto.setLastName(user.getLastName());
            userDetailsDto.setRole(user.getRole());
            return userDetailsDto;
        }
        return null;
    }

    @Override
    public UserDetailsResponseDto changeUserRole(String userName, String role) {
        Optional<User> userOptional = userRepository.findByUserName(userName);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            user.setRole(Role.valueOf(role));
            User savedUser = userRepository.save(user);
            UserDetailsResponseDto userDetailsDto = new UserDetailsResponseDto();
            userDetailsDto.setUserName(savedUser.getUsername());
            userDetailsDto.setFirstName(savedUser.getFirstName());
            userDetailsDto.setLastName(savedUser.getLastName());
            userDetailsDto.setRole(savedUser.getRole());
            return userDetailsDto;
        }
        return null;
    }
}
