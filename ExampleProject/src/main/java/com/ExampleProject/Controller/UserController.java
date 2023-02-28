package com.ExampleProject.Controller;

import com.ExampleProject.Model.User;
import com.ExampleProject.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public String getInfo() {
        return "hello";
    }

    @GetMapping("/all")
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @PostMapping("/add")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
         userRepository.deleteById(id);
         return ResponseEntity.ok("Usunieto");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
        User updateUser = userRepository.findById(id)
                .orElseThrow();
        updateUser.setName(user.getName());
        updateUser.setSurname(user.getSurname());
        updateUser.setAge(user.getAge());
        User userAfterUpdate = userRepository.save(updateUser);
        return ResponseEntity.ok(userAfterUpdate);
    }


    }
