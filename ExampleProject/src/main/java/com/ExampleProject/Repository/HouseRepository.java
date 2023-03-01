package com.ExampleProject.Repository;

import com.ExampleProject.Model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {
    List<House> findByName(String name);
    List<House> findHouseByUserName(String name);
}
