package com.maveric.userservice.service;

import com.maveric.userservice.dto.UserResponse;
import com.maveric.userservice.exceptionhandler.EmailNotFound;
import com.maveric.userservice.exceptionhandler.UserNotExist;
import com.maveric.userservice.model.User;

import java.util.List;

public interface UserService {
    public List<UserResponse> getUsers(Integer page, Integer pageSize);
    public UserResponse createUser(UserResponse userResponse);

    public UserResponse getUserDetails(String userId);

    public String deleteUser(String userId) throws UserNotExist;

    public UserResponse  getUserDetailsByEmail(String email) throws EmailNotFound;

    public UserResponse updateUser(long userId, User user) throws UserNotExist;


}
