package com.maveric.userservice.controller;

import com.maveric.userservice.constant.ErrorAndSuccessMessageConstants;
import com.maveric.userservice.dto.UserDto;
import com.maveric.userservice.dto.UserEmailDto;
import com.maveric.userservice.exception.UserIdMismatch;
import com.maveric.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import javax.validation.Valid;

import static com.maveric.userservice.constant.ErrorAndSuccessMessageConstants.USER_ID_MISMATCH;

@RestController
@RequestMapping("/api/v1")
public class UserServiceController {

    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceController.class);

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDto> getUserDetails(@PathVariable String userId,@RequestHeader(value = "userId") String headerUserId){
        if(userId.equals(headerUserId)) {
            UserDto userDetails = userService.getUserDetails(userId);
            logger.info(ErrorAndSuccessMessageConstants.USER_FOUND+userId);
            return ResponseEntity.status(HttpStatus.OK).body(userDetails);
        }else{
            logger.info(USER_ID_MISMATCH);
            throw new UserIdMismatch(USER_ID_MISMATCH);
        }
    }
}
