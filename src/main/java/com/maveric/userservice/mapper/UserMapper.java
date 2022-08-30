package com.maveric.userservice.mapper;

import com.maveric.userservice.dto.UserResponse;
import com.maveric.userservice.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="User")
public interface UserMapper {

    User map(UserResponse userResponse);

    UserResponse map(User user);

    List<User> map (List<UserResponse> userResponses);
}