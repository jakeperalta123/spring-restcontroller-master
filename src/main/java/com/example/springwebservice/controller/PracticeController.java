package com.example.springwebservice.controller;

import com.example.springwebservice.controller.dto.request.CreateUserRequest;
import com.example.springwebservice.controller.dto.response.StatusRseponse;
import com.example.springwebservice.model.entity.User;
import com.example.springwebservice.service.PracticeService;
import com.example.springwebservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500", maxAge = 3600)
@RestController
@RequestMapping("/practice/user")
public class PracticeController {
    @Autowired
    PracticeService practiceService;

    @GetMapping()
    //http://localhost:8080/practice/user?name=Jake&age=24
    public List<User> getUserByNameAndAge(@RequestParam String name,@RequestParam int age) {
        List<User> response = practiceService.getUserByNameAndAge(name, age);
        return response;
    }

    @PostMapping()
    public StatusRseponse createUser(@RequestBody CreateUserRequest request){
        String response = practiceService.createUser(request);
        return new StatusRseponse(response);
    }

    @PutMapping("/{id}")
    public StatusRseponse updateUser(@PathVariable int id, @RequestBody CreateUserRequest request){
        String response = practiceService.updateUser(id, request);
        return new StatusRseponse(response);
    }

    @DeleteMapping()
    public StatusRseponse deleteUserByNameAndAge(@RequestParam String name, @RequestParam int age){
        String response = practiceService.deleteUserByNameAndAge(name, age);
        return new StatusRseponse(response);
    }



}
