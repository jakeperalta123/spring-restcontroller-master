package com.example.springwebservice.service;

import com.example.springwebservice.controller.dto.request.CreateUserRequest;
import com.example.springwebservice.model.UserRepository;
import com.example.springwebservice.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PracticeService {
    @Autowired
    private UserRepository userRepository;
    public List<User> getUserByNameAndAge(String name, int age){
        List<User> response = userRepository.findByNameAndAge(name, age);
        return response;
    }

    public String createUser(CreateUserRequest request){
        userRepository.createUserBySql(request.getId(), request.getName(), request.getAge());
        return "OK";
    }

    public String updateUser(int id, CreateUserRequest request){
        int count = userRepository.updateUserBySql(request.getAge(), request.getName(), id);
        String response  = "Fail";
        if(0 < count){
            response = "OK";
        }
        return response;
    }

    public String  deleteUserByNameAndAge(String name, int age){
        userRepository.deleteUserByNameAndAge(name, age);
        return "OK";
    }
}
