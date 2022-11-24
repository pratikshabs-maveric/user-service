package com.maveric.userservice.service;

import com.maveric.userservice.constant.Gender;
import com.maveric.userservice.converter.ModelDtoConverter;
import com.maveric.userservice.dto.UserDto;
import com.maveric.userservice.dto.UserEmailDto;
import com.maveric.userservice.model.User;
import com.maveric.userservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.Optional;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
 class UserServiceTest {

    @Mock
    private UserRepository mockedUserRepository;

    @Mock
    private ModelDtoConverter modelDtoConverter;

    @Mock
    private Page page;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldReturnUserWhenGetUserInvoked() throws Exception {
        when(mockedUserRepository.findById("2c9cf08182f36d5a0182f3731f210000")).thenReturn(
                Optional.of(getSampleUser()));
        when(modelDtoConverter.entityToDto(any(User.class))).thenReturn(getSampleDtoUser());

        UserDto user = userService.getUserDetails("2c9cf08182f36d5a0182f3731f210000");

        assertNotNull(user);
        assertSame(user.getEmail(),getSampleUser().getEmail());

    }

    @Test
    void shouldReturnUserWhenGetUsersInvoked() throws Exception {
        Page<User> pageResponse = new PageImpl<>(Arrays.asList(getSampleUser(),getSampleUser()));
        when(mockedUserRepository.findAll(any(Pageable.class))).thenReturn(pageResponse);
        when(modelDtoConverter.entityToDto((List<User>) any())).thenReturn(Arrays.asList
                (getSampleDtoUser(),getSampleDtoUser()));

        List<UserDto> user = userService.getUsersDetails(0,10);

        assertNotNull(user);

    }

    @Test
    void shouldReturnUserWhenGetUserByEmailInvoked() throws Exception {
        when(mockedUserRepository.findByEmail("devi@gmail.com")).
                thenReturn(Optional.of(getSampleUser()));
        when(modelDtoConverter.entityToDtoForEmail(any(User.class))).thenReturn(getSampleEmailDtoUser());

        UserEmailDto user = userService.getUserDetailsByEmail("devi@gmail.com");

        assertNotNull(user);
        assertSame(user.getEmail(),getSampleUser().getEmail());
    }

    @Test
    void shouldReturnMessageWhenDeleteUserMethodInvoked() throws Exception {
        when(mockedUserRepository.findById("2c9cf08182f36d5a0182f3731f210000")).
                thenReturn(Optional.of(getSampleUser()));
        willDoNothing().given(mockedUserRepository).deleteById("2c9cf08182f36d5a0182f3731f210000");

        String message = userService.deleteUser("2c9cf08182f36d5a0182f3731f210000");

        assertNotNull(message);
        assertSame("User Deleted Successfully",message);
    }

    @Test
     void shouldReturnUserWhenUpdateUserInvoked() throws Exception {
        when(mockedUserRepository.findById("2c9cf08182f36d5a0182f3731f210")).
                thenReturn(Optional.ofNullable(getSampleUser()));
        when(modelDtoConverter.entityToDto(mockedUserRepository.save(getSampleUser()))).thenReturn(getSampleDtoUser());

        UserDto user = userService.updateUserDetails(getSampleDtoUser(), "2c9cf08182f36d5a0182f3731f210");

        assertNotNull(user);
        assertSame(user.getEmail(),getSampleUser().getEmail());
    }

    @Test
     void shouldReturnUserWhenCreateUserInvoked() throws Exception {

        when(modelDtoConverter.dtoToEntity(any(UserDto.class))).thenReturn(getSampleUser());
        when(mockedUserRepository.findByEmail(any())).thenReturn( Optional.empty ());
        when(mockedUserRepository.save(any(User.class))).thenReturn(getSampleUser());
        when(modelDtoConverter.entityToDto(any(User.class))).thenReturn(getSampleDtoUser());

        UserDto userDto = userService.createUserDetails(getSampleDtoUser());

        assertNotNull(userDto);
        assertSame(userDto.getEmail(),getSampleDtoUser().getEmail());

    }

    public User getSampleUser(){
        User user = new User();
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

    public List<User> getSampleUsers(){
        List<User> userList = new ArrayList<User>();
        User user = new User();
        User sampleUser = new User();
        user.setFirstName("Sneha");
        user.setLastName("Samane");
        user.setEmail("sneha@gmail.com");
        sampleUser.setFirstName("Naval");
        sampleUser.setLastName("Samane");
        sampleUser.setEmail("shreeharsha1@gmail.com");
        sampleUser.setPassword("naval");
        sampleUser.setGender(Gender.FEMALE);
        sampleUser.setDateOfBirth("2021-02-02");
        sampleUser.setAddress("America");
        sampleUser.setPhoneNumber("7744001665");

        userList.add(user);
        userList.add(sampleUser);

        return userList;
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

    public UserEmailDto getSampleEmailDtoUser(){
        UserEmailDto user = new UserEmailDto();
        user.setFirstName("Sneha");
        user.setLastName("Samane");
        user.setEmail("sneha@gmail.com");
        user.setPassword("sneha");
        user.setGender(Gender.MALE);
        user.setDateOfBirth("2022-02-02");
        user.setAddress("America");
        user.setPhoneNumber("7744001665");
        return user;
    }
    public List<UserDto> getSampleUserForGetUsers(){
        List<UserDto> userList = new ArrayList<UserDto>();
        UserDto user = new UserDto();
        UserDto sampleUser = new UserDto();
        user.setFirstName("Sneha");
        user.setLastName("Samane");
        user.setEmail("sneha@gmail.com");
        user.setPassword("sneha");
        user.setGender(Gender.FEMALE);
        user.setDateOfBirth("2022-02-02");
        user.setAddress("America");
        user.setPhoneNumber("7744001665");
        sampleUser.setFirstName("Naval");
        sampleUser.setLastName("Samane");
        sampleUser.setEmail("naval@gmail.com");
        sampleUser.setPassword("naval");
        sampleUser.setGender(Gender.FEMALE);
        sampleUser.setDateOfBirth("2021-02-02");
        sampleUser.setAddress("America");
        sampleUser.setPhoneNumber("8956231245");

        userList.add(user);
        userList.add(sampleUser);

        return userList;

    }

}
