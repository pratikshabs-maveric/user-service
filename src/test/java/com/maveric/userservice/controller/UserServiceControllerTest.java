package com.maveric.userservice.controller;

import com.maveric.userservice.dto.UserDto;
import com.maveric.userservice.exception.UserNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maveric.userservice.constant.Gender;
import com.maveric.userservice.model.User;
import com.maveric.userservice.repository.UserRepository;
import com.maveric.userservice.service.UserService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(UserServiceController.class)
@Tag("Integration tests")
 class UserServiceControllerTest {
    private static final String API_V1_USERS = "/api/v1/users";

    private static final String API_V1_USERS_EMAIL = "/api/v1/users/getUserByEmail";

    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    BCryptPasswordEncoder bCryptPasswordEncoder;

    //getUser
    @Test
    void shouldGetUserWhenRequestMadeToGetUser() throws Exception{
        mvc.perform(get(API_V1_USERS+"/2c9cf08182f36d5a0182f3731f210000").
                        header("userId","2c9cf08182f36d5a0182f3731f210000"))
                .andExpect(status().isOk())
                .andDo(print());

    }
    @Test
    void shouldReturnInternalServerWhenDbReturnsError() throws Exception{
        when(userService.getUserDetails("2c9cf08182f36d5a0182f3731f210000")).
                thenThrow(new UserNotFoundException("2c9cf08182f36d5a0182f3731f210000"));
        mvc.perform(get(API_V1_USERS+"/2c9cf08182f36d5a0182f3731f210000").
                        header("userId","2c9cf08182f36d5a0182f3731f210000"))
                .andExpect(status().isNotFound())
                .andDo(print());

    }

    //get Users
    @Test
    void shouldGetUserWhenRequestMadeToGetUsers() throws Exception {
        mvc.perform(get(API_V1_USERS + "?page=0&pageSize=10"))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    void shouldReturnInternalServerWhenDbReturnsErrorForGetUsers() throws Exception{
        when(userService.getUsersDetails(0,10)).thenThrow(new IllegalArgumentException());
        mvc.perform(get(API_V1_USERS+"?page=-1"))
                .andExpect(status().is5xxServerError())
                .andDo(print());
    }


    //getUserByEmail
    @Test
    void shouldGetUserWhenRequestMadeToGetUserByEmail() throws Exception {
        mvc.perform(get(API_V1_USERS_EMAIL + "/sneha@gmail.com"))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    void shouldReturnInternalServerWhenDbReturnsErrorForGetUserByEmail() throws Exception{
        when(userService.getUserDetailsByEmail("sneha@gmail.com")).
                thenThrow(new UserNotFoundException("sneha@gmail.com"));
        mvc.perform(get(API_V1_USERS_EMAIL+"/sneha@gmail.com"))
                .andExpect(status().isNotFound())
                .andDo(print());

    }

    //deleteUser
    @Test
    void shouldDeleteUserWhenRequestMadeToDeleteUser() throws Exception{
        mvc.perform(delete(API_V1_USERS+"/2c9cf08182f36d5a0182f3731f210000").
                        header("userId","2c9cf08182f36d5a0182f3731f210000"))
                .andExpect(status().isOk())
                .andDo(print());

    }
    @Test
    void shouldReturnInternalServerWhenDbReturnsErrorForDelete() throws Exception{
       when(userService.deleteUser("2c9cf08182f36d5a0182f3731f2100")).
               thenThrow(new UserNotFoundException("2c9cf08182f36d5a0182f3731f2100"));
        mvc.perform(delete(API_V1_USERS+"/2c9cf08182f36d5a0182f3731f2100").
                        header("userId","2c9cf08182f36d5a0182f3731f2100"))
                .andExpect(status().isNotFound())
                .andDo(print());

    }


    //createUser
     @Test
     void shouldCreateUserWhenRequestMadeToCreateUser() throws Exception{
        when(userService.createUserDetails(any(UserDto.class))).thenReturn(getSampleDtoUser());
        mvc.perform(post(API_V1_USERS).contentType(MediaType.APPLICATION_JSON).
                        content(mapper.writeValueAsString(getSampleUser())))
                .andExpect(status().isCreated())
                .andDo(print());

    }
    @Test
     void shouldThrowBadRequestWhenUserDetailsAreWrong() throws Exception {
        User user = new User();
        user.setFirstName(null);
        user.setLastName("Samane");
        user.setEmail("sneha@gmail.com");
        user.setPassword("sneha");
        user.setGender(Gender.FEMALE);
        user.setDateOfBirth("2022-02-02");
        user.setAddress("America");
        user.setPhoneNumber("7744001665");
        mvc.perform(post(API_V1_USERS).contentType(MediaType.APPLICATION_JSON).content(mapper.
                        writeValueAsString(user)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }
    @Test
    void shouldReturnInternalServerWhenDbReturnsErrorForCreate() throws Exception {
        when(userService.createUserDetails(Mockito.any(UserDto.class))).thenThrow(new IllegalArgumentException());
        mvc.perform(post(API_V1_USERS).contentType(MediaType.APPLICATION_JSON).
                        content(mapper.writeValueAsString(getSampleUser())))
                .andExpect(status().isInternalServerError())
                .andDo(print());

    }


    //updateUser
    @Test
    void shouldUpdateUserWhenRequestMadeToUpdateUser() throws Exception {
        mvc.perform(put(API_V1_USERS + "/2c9cf08182f36d5a0182f3731f210000").
                        header("userId","2c9cf08182f36d5a0182f3731f210000").
                        contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(getSampleUser())))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    void shouldThrowBadRequestWhenUpdateUserDetailsAreWrong() throws Exception{
        User user = new User();
        user.setFirstName(null);
        user.setLastName("Samane");
        user.setEmail("sneha@gmail.com");
        user.setPassword("sneha");
        user.setGender(Gender.MALE);
        user.setDateOfBirth("2022-02-02");
        user.setAddress("America");
        user.setPhoneNumber("7744001665");
        mvc.perform(put(API_V1_USERS+"/2c9cf08182f36d5a0182f3731f210000").
                        contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
     void shouldReturnInternalServerWhenDbReturnsErrorForUpdate() throws Exception {
        when(userService.updateUserDetails(Mockito.any(UserDto.class), eq("2c9cf08182f36d5a0182f3731f210000"))).
                thenThrow(new IllegalArgumentException());
        mvc.perform(put(API_V1_USERS + "/2c9cf08182f36d5a0182f3731f210000").
                        contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(getSampleUser())))
                .andExpect(status().isInternalServerError())
                .andDo(print());
    }

    public User getSampleUser(){
        User user = new User();
        user.setId("2c9cf08182f36d5a0182f3731f210000");
        user.setFirstName("Sneha");
        user.setLastName("Samane");
        user.setEmail("sneha@gmail.com");
        user.setPassword("sneha");
        user.setGender(Gender.FEMALE);
        user.setDateOfBirth("2022-02-02");
        user.setAddress("America");
        user.setPhoneNumber("7744001665");
        return user;

    }

    public UserDto getSampleDtoUser(){
        UserDto user = new UserDto();
        user.setFirstName("Sneha");
        user.setLastName("Samane");
        user.setEmail("sneha@gmail.com");
        user.setPassword("sneha");
        user.setGender(Gender.FEMALE);
        user.setDateOfBirth("2022-02-02");
        user.setAddress("America");
        user.setPhoneNumber("7744001665");
        return user;
    }
}
