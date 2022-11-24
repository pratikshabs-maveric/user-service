package com.maveric.userservice.converter;

import com.maveric.userservice.constant.Gender;
import com.maveric.userservice.dto.Error;
import com.maveric.userservice.dto.UserDto;
import com.maveric.userservice.dto.UserEmailDto;
import com.maveric.userservice.exception.UserNotFoundException;
import com.maveric.userservice.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import static org.mockito.ArgumentMatchers.any;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ModelToDtoTest {

    @InjectMocks
    private ModelDtoConverter modelDtoConverter;

    @Test
    void handleEntityToDto() {
        User user =getSampleUser();
        UserDto userDto = modelDtoConverter.entityToDto(user);
        assertNotNull(userDto.getEmail());
    }

    @Test
    void handleDtoToEntity() {
        UserDto userDto =getSampleDtoUser();
        User user = modelDtoConverter.dtoToEntity(userDto);
        assertNotNull(user.getEmail());
    }

    @Test
    void handleEntityToDtoEmail() {
        User user =getSampleUser();
        UserEmailDto userEmailDto = modelDtoConverter.entityToDtoForEmail(user);
        assertNotNull(userEmailDto.getEmail());
    }

    @Test
    void shouldReturnErrorWhenLastnameIsEmptyEntityToDtoEmail() {
        User user =getSampleUser();
        UserDto userDto = modelDtoConverter.entityToDto(user);
        assertNull(userDto.getPassword());
    }

    public User getSampleUser(){
        User user = new User();
        user.setFirstName("Sneha");
        user.setEmail("sneha@gmail.com");
        user.setGender(Gender.FEMALE);
        user.setDateOfBirth("2022-02-02");
        user.setAddress("America");
        user.setPhoneNumber("7744001665");

        return user;
    }

    public UserDto getSampleDtoUser(){
        UserDto user = new UserDto();
        user.setFirstName("Sneha");
        user.setEmail("sneha@gmail.com");
        user.setPassword("sneha");
        user.setGender(Gender.FEMALE);
        user.setDateOfBirth("2022-02-02");
        user.setAddress("America");
        user.setPhoneNumber("7744001665");
        return user;
    }
}
