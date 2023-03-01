package com.ExampleProject.Controller;

import com.ExampleProject.Model.House;
import com.ExampleProject.Model.User;
import com.ExampleProject.Repository.HouseRepository;
import com.ExampleProject.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/house")
public class HouseController {

    @Autowired
    private HouseRepository houseRepository;
    @Autowired
    private UserRepository userRepository;



    @GetMapping("/all")
    public ResponseEntity<List<House>> getAllHouse() {
        List<House> _house = houseRepository.findAll();
        return ResponseEntity.ok(_house);
    }

    @GetMapping("/one/{name}")
    public ResponseEntity<List<House>> getAllByNameHouse(@PathVariable(value = "name") String name) {
        List<House> _house = houseRepository.findByName(name);
        return ResponseEntity.ok(_house);
    }

    @GetMapping("/two/{name}")
    public ResponseEntity<List<House>> getAllHouseByNameUser(@PathVariable(value = "name") String name) {
        List<House> _house = houseRepository.findHouseByUserName(name);
        return ResponseEntity.ok(_house);
    }

    @PostMapping("/add/{userId}")
    public ResponseEntity<House> createHouse(@PathVariable(value = "userId") Long userId, @RequestBody House house) {
        House _house = userRepository.findById(userId).map(user -> {
            house.setUser(user);
            return houseRepository.save(house);
        }).orElseThrow();

        return new ResponseEntity<>(_house, HttpStatus.CREATED);
    }
}
