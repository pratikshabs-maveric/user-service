package com.maveric.userservice.mapper;

import com.maveric.userservice.dto.UserResponse;
import com.maveric.userservice.model.User;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.maveric.userservice.UserServiceApplicationTests.getUser;
import static com.maveric.userservice.UserServiceApplicationTests.getUserDto;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class UserMapperImplTest {
    UserMapperImpl userMapper=new UserMapperImpl();
    @Test
    void map() {
        UserResponse userDto= (UserResponse) getUserDto();
        User user = userMapper.map(userDto);
        assertEquals(((UserResponse) getUserDto()).getGender(), user.getGender());
        assertEquals(((UserResponse) getUserDto()).getId(), user.getId());
    }

    @Test
    void testMap() {
        User user=getUser();
        UserResponse userdto = userMapper.map(user);
        assertEquals(getUser().getGender(), userdto.getGender());
        assertEquals(getUser().getId(), userdto.getId());
    }

//    @Test
//    void mapToModel() {
//
//        List<User> users = userMapper.mapToModel(Arrays.asList(getUserDto(),getUserDto()));
//        assertEquals(2,users.size());
//    }

//    @Test
//    void mapToDto() {
//        List<UserDto> users = userMapper.mapToDto(Arrays.asList(getUser(),getUser()));
//        assertEquals(2,users.size());
//    }
}