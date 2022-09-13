package com.maveric.userservice.service;

import com.maveric.userservice.dto.UserResponse;
import com.maveric.userservice.exceptionhandler.EmailAlreadyExist;
import com.maveric.userservice.exceptionhandler.EmailNotFound;
import com.maveric.userservice.exceptionhandler.UserNotExist;
import com.maveric.userservice.mapper.UserMapper;
import com.maveric.userservice.model.User;
import com.maveric.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

    @Autowired
    PasswordEncoder passwordEncoder;
    public List<UserResponse> getUsers(Integer page, Integer pageSize) {
        Pageable paging = (Pageable) PageRequest.of(page, pageSize);
        Page<User> pageResult = repository.findAll(paging);
        if(pageResult.hasContent()) {
            return pageResult.getContent().stream()
                    .map(
                            user -> mapper.map(user)
                    ).collect(
                            Collectors.toList()
                    );
        } else {
            return new ArrayList<>();
        }
    }
    public UserResponse createUser(UserResponse userResponse) {
//        User user = mapper.map(userResponse);
//        User userResult = repository.save(user);
//        return  mapper.map(userResult);



        String pass = passwordEncoder.encode(userResponse.getPassword());
        userResponse.setPassword(pass);
        User user = mapper.map(userResponse);
        User userResult = repository.save(user);
        return  mapper.map(userResult);


    }

    @Override
    public UserResponse getUserDetails(String userId) {
        User getResult=repository.findById(Long.valueOf(userId)).orElseThrow(() -> new UserNotExist("user not found"));
        return mapper.map(getResult);
    }

    @Override
    public String deleteUser(String userId) {

        if(repository.findById(Long.valueOf(userId)).isPresent()){
            repository.deleteById(Long.valueOf(userId));
        }
        else {
            throw new UserNotExist("No Users Found");
        }
        return "User Successfully Deleted";
    }

    public UserResponse getUserDetailsByEmail(String email) {
        User getEmailResult = repository.findByEmail(email);
        if ( getEmailResult == null ) {
            throw new EmailNotFound("Email not Found");
        }
        return mapper.map(getEmailResult);
    }

    @Override
    public UserResponse updateUser(long userId, User user) {

        User updateUser = repository.findById(userId).orElseThrow(()-> new UserNotExist("User Not Found"));
        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getLastName());
        updateUser.setMiddleName(user.getMiddleName());
        updateUser.setPhoneNumber(user.getPhoneNumber());
//        updateUser.setEmail(user.getEmail());


        if (repository.findByEmail(user.getEmail())!=null) {
            throw new EmailAlreadyExist("Email Already Exist");
        }
        else{
            updateUser.setEmail(user.getEmail());
        }

        updateUser.setAddress(user.getAddress());
        updateUser.setDateOfBirth(user.getDateOfBirth());
        updateUser.setGender(user.getGender());
        return mapper.map(repository.save(updateUser));
    }
}
