package com.maveric.userservice.mapper;

import com.maveric.userservice.dto.UserResponse;
import com.maveric.userservice.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public User map(UserResponse userResponse) {
        return new User(
                userResponse.getId(),
                userResponse.getFirstName(),
                userResponse.getLastName(),
                userResponse.getMiddleName(),
                userResponse.getPhoneNumber(),
                userResponse.getEmail(),
                userResponse.getAddress(),
                userResponse.getDateOfBirth(),
                userResponse.getGender(),
                userResponse.getPassword()
        );
    }

    @Override
    public UserResponse map(User user) {
        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getMiddleName(),
                user.getPhoneNumber(),
                user.getEmail(),
                user.getAddress(),
                user.getDateOfBirth(),
                user.getGender(),
                user.getPassword()
        );
    }

    @Override
    public List<User> map(List<UserResponse> userResponses) {
        List<User> list = new ArrayList<User>(userResponses.size());
        for(UserResponse userResult:userResponses)
        {
            list.add(map(userResult));
        }
        return list;
    }


//    @Override
//    public List<User> mapToModel(List<UserResponse> userResponse) {
//        if(!UserResponse.isEmpty())
//            return UserResponse.stream().map(user -> new User(
//                    userResponse.getId(),
//                    userResponse.getFirstName(),
//                    userResponse.getLastName(),
//                    userResponse.getMiddleName(),
//                    userResponse.getPhoneNumber(),
//                    userResponse.getEmail(),
//                    userResponse.getAddress(),
//                    userResponse.getDateOfBirth(),
//                    userResponse.getGender(),
//                    userResponse.getPassword()
//            )).toList();
//        else
//            return Collections.<User>emptyList();
//    }
}
