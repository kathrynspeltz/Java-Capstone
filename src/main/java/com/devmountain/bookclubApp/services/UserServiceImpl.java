package com.devmountain.bookclubApp.services;

import com.devmountain.bookclubApp.dtos.UserDto;
import com.devmountain.bookclubApp.entities.User;
import com.devmountain.bookclubApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public List<String> addUser(UserDto userDto) {
        List<String> response = new ArrayList<>();
        User user = new User(userDto);
        userRepository.saveAndFlush(user);
        response.add("User Added Successfully");
        return response;
    }

    @Override
    @javax.transaction.Transactional
    public List<String> userLogin(UserDto userDto) {
        List<String> response = new ArrayList<>();
        Optional<User> userOptional = userRepository.findByUsername(userDto.getUsername());
        if (userOptional.isPresent()) {
            if (passwordEncoder.matches(userDto.getPassword(), userOptional.get().getPassword())){
                response.add("User Login Successful");
                response.add(String.valueOf(userOptional.get().getId()));
            } else {
                response.add("Username or password incorrect");
            }
        } else {
            response.add("Username or password incorrect");
        }
        return response;
    }
}
