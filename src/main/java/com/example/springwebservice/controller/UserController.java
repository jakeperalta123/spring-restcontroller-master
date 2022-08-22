package com.example.springwebservice.controller;

import com.example.springwebservice.controller.dto.request.CreateUserRequest;
import com.example.springwebservice.controller.dto.request.UpdateUserRequest;
import com.example.springwebservice.controller.dto.response.StatusRseponse;
import com.example.springwebservice.model.entity.User;
import com.example.springwebservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://127.0.0.1:5500", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;//要使用UserService的東西記得注入UserService

    @GetMapping()
    public List<User> getUserList(@RequestParam(required = false) Integer age) {
        List<User> response = userService.findUserList(age);
        return response;
    }

    @GetMapping("/{id}")//會放在PathVariable的參數都是唯一識別(像User Id就是獨一無二的)
    public User getUser(@PathVariable int id) {
        User response = userService.getUser(id);
        return response;
    }

    @PostMapping()
    public StatusRseponse createUser(@RequestBody CreateUserRequest request) {
        String response = userService.createUser(request);
        return new StatusRseponse(response);
    }

    @PutMapping("/{id}")
    public StatusRseponse updateUser(@PathVariable int id, @RequestBody UpdateUserRequest request) {
        String response = userService.updateUser(id, request);
        return new StatusRseponse(response);
    }

    @DeleteMapping("/{id}")
    public StatusRseponse deleteUser(@PathVariable int id) {
        String response = userService.deleteUserById(id);
        return new StatusRseponse(response);
    }

    @GetMapping("/age/{age}")
    public List<User> getUserByAgeGreaterEqual(@PathVariable int age) {
        List<User> response = userService.findUserGreaterThanEqual(age);
        return response;
    }

    @GetMapping("/age/age-desc")
    public List<User> getUserByAgeDesc() {
        List<User> response = userService.findUserByAgeDesc();
        return response;
    }

    @GetMapping("/unique-user")
    public List<String> getUniqueUser() {
       List<String> nameList = userService.getUniqueUser();
       return nameList;
    }
    @GetMapping("/IdNameMap")
    public Map<Integer, String>getUserIdName(){
        Map<Integer, String> response = userService.getUserIdNameMap();
        return response;
    }

    @GetMapping("/getFirstMatchByName")
    public User getFirstMatchByName(@RequestParam String name){
        User response = userService.getFirstMatchByName(name);
        return response;
    }

    @GetMapping("/findUserOrderByNameAndAge")
    public List<User> getUserOrderByNameAndAge(){
        List<User> response = userService.findUserOrderByNameAndAge();
        return response;
    }

    @GetMapping("/findAllUserNameAndAge")
    public String getAllUserNameAndAge(){
        String response = userService.FindUserNameAndAge();
        return response;
    }


} // Class end
