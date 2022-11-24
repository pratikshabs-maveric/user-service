package com.maveric.userservice.converter;

import com.maveric.userservice.dto.UserDto;
import com.maveric.userservice.dto.UserEmailDto;
import com.maveric.userservice.model.User;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class ModelDtoConverter {

    public List<UserDto> entityToDto(List<User> user) {
        List<UserDto> userDto = new ArrayList<>();
        user.stream().forEach(fetchUser -> {
            UserDto singleUser = new UserDto();
            singleUser.setId(fetchUser.getId());
            singleUser.setFirstName(fetchUser.getFirstName());
            singleUser.setMiddleName(fetchUser.getMiddleName());
            singleUser.setLastName(fetchUser.getLastName());
            singleUser.setEmail(fetchUser.getEmail());
            singleUser.setPhoneNumber(fetchUser.getPhoneNumber());
            singleUser.setAddress(fetchUser.getAddress());
            singleUser.setGender(fetchUser.getGender());
            singleUser.setDateOfBirth(fetchUser.getDateOfBirth());
            userDto.add(singleUser);
        });
        return userDto;
    }

    public UserDto entityToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setMiddleName(user.getMiddleName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setAddress(user.getAddress());
        userDto.setGender(user.getGender());
        userDto.setDateOfBirth(user.getDateOfBirth());

        return userDto;

    }

    public UserEmailDto entityToDtoForEmail(User user){
        UserEmailDto userEmailDto = new UserEmailDto();
        userEmailDto.setId(user.getId());
        userEmailDto.setFirstName(user.getFirstName());
        userEmailDto.setMiddleName(user.getMiddleName());
        userEmailDto.setLastName(user.getLastName());
        userEmailDto.setEmail(user.getEmail());
        userEmailDto.setPhoneNumber(user.getPhoneNumber());
        userEmailDto.setAddress(user.getAddress());
        userEmailDto.setGender(user.getGender());
        userEmailDto.setDateOfBirth(user.getDateOfBirth());
        userEmailDto.setPassword(user.getPassword());

        return userEmailDto;

    }

    public User dtoToEntity(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setMiddleName(userDto.getMiddleName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setAddress(userDto.getAddress());
        user.setGender(userDto.getGender());
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setPassword(userDto.getPassword());

        return user;

    }
}
