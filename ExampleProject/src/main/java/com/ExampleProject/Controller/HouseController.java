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

//        public List<House> getAllHouse() { return.houseRepository.findAll();} //nie działa zwraca 404 not found

        // Dlaczego nie działa zwykla return List<House> ?
        // Tylko musi być ResponseEntity<List<House>> ?

//        for(House h : _house){
//           ResponseEntity.ok(h);
//        }

//        _house.stream().map(house -> {
//            User user = house.getUser();
//            userRepository.findById(user.getId());
//            return userRepository.findById(user.getId());
//        });

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
