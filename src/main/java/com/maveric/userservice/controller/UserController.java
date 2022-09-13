package com.maveric.userservice.controller;

import com.maveric.userservice.dto.UserResponse;
import com.maveric.userservice.model.User;
import com.maveric.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/v1/users")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    // Get the list of users
    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer pageSize){
        List<UserResponse> userResponses = userService.getUsers(page,pageSize);
        return new ResponseEntity<List<UserResponse>>(userResponses, HttpStatus.OK);
    }

    // Create user using REST API
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid  @RequestBody UserResponse userResponse) {
        UserResponse userDtoResponse = userService.createUser(userResponse);
        return new ResponseEntity<UserResponse>(userDtoResponse, HttpStatus.OK);
    }

   // Get users details
    @GetMapping("{userId}")
    public ResponseEntity<UserResponse> getUserDetails(@PathVariable String userId) {
        UserResponse userDtoResponse = userService.getUserDetails(userId);
        return new ResponseEntity<UserResponse>(userDtoResponse, HttpStatus.OK);
    }

    // Update user
    @PutMapping("{userId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable long userId, @RequestBody User User){
        return new ResponseEntity<UserResponse>(userService.updateUser(userId, User), HttpStatus.OK);
    }

    // Delete user using REST API
    @DeleteMapping("{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        String result = userService.deleteUser(userId);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }

    // Get user by email ID
    @GetMapping("getUserByEmail/{email}")
    public ResponseEntity<UserResponse> getUserDetailsByEmail(@PathVariable String email) {
        UserResponse res = userService.getUserDetailsByEmail(email);
        return new ResponseEntity<UserResponse>(res, HttpStatus.OK);
    }
}
