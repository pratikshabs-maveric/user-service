package com.maveric.userservice.service;

import com.maveric.userservice.constant.ErrorAndSuccessMessageConstants;
import com.maveric.userservice.converter.ModelDtoConverter;
import com.maveric.userservice.dto.UserDto;
import com.maveric.userservice.dto.UserEmailDto;
import com.maveric.userservice.exception.EmailDuplicationException;
import com.maveric.userservice.exception.UserNotFoundException;
import com.maveric.userservice.model.User;
import com.maveric.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.Optional;
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelDtoConverter modelDtoConverter;

    public UserDto getUserDetails(String userId) {
        User optionalUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        return modelDtoConverter.entityToDto(optionalUser);
    }
    public List<UserDto> getUsersDetails(int page, int pageSize) {
        Page<User> user = userRepository.findAll(PageRequest.of(page, pageSize));
        List<User> listUser = user.getContent();
        return modelDtoConverter.entityToDto(listUser);
    }

    public UserEmailDto getUserDetailsByEmail(String emailId) {
        User optionalUser = userRepository.findByEmail(emailId).orElseThrow(() -> new UserNotFoundException(emailId));

        return modelDtoConverter.entityToDtoForEmail(optionalUser);
    }


    public UserDto updateUserDetails(UserDto userDto, String userId){

        Optional<User> userFromDb = userRepository.findById(userId);
        if(userFromDb.isPresent()) {
            User newUser = userFromDb.get();
            newUser.setFirstName(userDto.getFirstName());
            newUser.setLastName(userDto.getLastName());
            newUser.setMiddleName(userDto.getMiddleName());
            newUser.setPhoneNumber(userDto.getPhoneNumber());
            newUser.setEmail(userDto.getEmail());
            newUser.setAddress(userDto.getAddress());
            newUser.setDateOfBirth(userDto.getDateOfBirth());
            newUser.setGender(userDto.getGender());

            return modelDtoConverter.entityToDto(userRepository.save(newUser));
        }else{
            throw new UserNotFoundException(userId);
        }
    }

    public UserDto createUserDetails(UserDto userDto){
        User user = modelDtoConverter.dtoToEntity(userDto);
        Optional<User> emailUser = userRepository.findByEmail(user.getEmail());
        if(!emailUser.isPresent()) {
            User newUser = userRepository.save(user);
            return modelDtoConverter.entityToDto(newUser);
        }else{
            throw new EmailDuplicationException(user.getEmail());
        }

    }

    public String deleteUser(String userId){
        userRepository.findById(userId);
        userRepository.deleteById(userId);
        return ErrorAndSuccessMessageConstants.SUCCESS_DELETE_USER;
    }
}
