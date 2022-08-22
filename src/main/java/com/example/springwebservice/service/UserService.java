package com.example.springwebservice.service;

import com.example.springwebservice.controller.dto.request.CreateUserRequest;
import com.example.springwebservice.controller.dto.request.UpdateUserRequest;
import com.example.springwebservice.model.UserRepository;
import com.example.springwebservice.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.lang.model.element.Name;
import javax.websocket.server.PathParam;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

//如果程式出現nullpointerexception，到Service裡補上相關檢查邏輯
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    List<User> userList;
    //新增一個User資料

    //更改某個User資料
    public String updateUser(int id, UpdateUserRequest request) {
        //先確認有沒有這筆資料
        User user = userRepository.findById(id);
        //要被比較的值要放後面，確定的值放前面
        if (null == user) {
            return "No such ID you stupid ass motherFucker!!!";
        }
        //將要更改的值塞進去
        user.setName(request.getName());
        user.setAge(request.getAge());

        //儲存進DB
        userRepository.save(user);
        //回傳OK告訴Controller更改成功
        return "OK";
    }

    public String createUser(CreateUserRequest request) {
        //新增一個空的User entity = 新增一筆空的資料
        User user = new User();
        //塞好資料:user裡的資料是從request來的
        user.setId(request.getId());
        user.setName(request.getName());
        user.setAge(request.getAge());
        //把剛剛新增的entity(user)儲存進DB
        userRepository.save(user);
        //回傳OK告訴Controller完成儲存
        return "OK";
    }

    public List<User> getUserList() {
        List<User> response = userRepository.findAll();//用findAll會找出出全部資料，所以用List裝
        return response;
    }

    public User getUser(int id) {
        User response = userRepository.findById(id);
        return response;
    }


    public List<String> getUniqueUser() {
        List<String> nameList = new ArrayList<>();
        List<String> nameList1 = new ArrayList<>();
        List<User> userList1 = userRepository.findAll();
        for (User user : userList1) {
            nameList.add(user.getName());
        }
        nameList1 = nameList.stream().distinct().sorted().collect(Collectors.toList());
        return nameList1;
    }

    public Map<Integer, String> getUserIdNameMap() {
        List<User> userList = userRepository.findAll();
        Map<Integer, String> userIdNameMap = new HashMap<>();
        userList.stream()
                .forEach(e -> userIdNameMap.put(e.getId(), e.getName()));
        return userIdNameMap;
    }

    public User getFirstMatchByName(String name) {
        List<User> userList1 = userRepository.findAll();
        System.out.println(name);

        List<User> filteredUsers = userList1.stream().filter(x -> x.getName().equals(name)).collect(Collectors.toList());
        if (!filteredUsers.isEmpty()) {
            return filteredUsers.get(0);
        }

        System.out.println("No such user");
        return null;
    }


    public String deleteUserById(int id) {
        User user = userRepository.findById(id);
        if (null == user) {
            return "沒有這個使用者妳是在刪除沙小啦";
        }
        Long count = userRepository.deleteById(id);
        return "OK";
    }

    public List<User> findUserGreaterThanEqual(int age) {
        List<User> response = userRepository.findByAgeGreaterThanEqual(age);
        return response;
    }

    public List<User> findUserByAgeDesc() {
        List<User> response = userRepository.findByOrderByAgeDesc();
        return response;
    }

    public List<User> findUserList(Integer age) {
        List<User> response = new ArrayList<>();
        if (age != null) {
            response = userRepository.findByAgeGreaterThanEqual(age);
        } else {
            response = userRepository.findAll();
        }
        return response;
    }

    public List<User> findUserOrderByNameAndAge() {
        List<User> userList1 = userRepository.findUserOrderByNameAndAge();
        return userList1;
    }

    public String FindUserNameAndAge() {
        List<User> userList1 = userRepository.findAll();
        List<String> nameAndAge = new ArrayList<>();
        for (User user : userList1) {
            nameAndAge.add(user.getName() + ", " + user.getAge());
        }
        String allUserNameAndAge = String.join("|", nameAndAge);
        return allUserNameAndAge;
    }


} // Class end
