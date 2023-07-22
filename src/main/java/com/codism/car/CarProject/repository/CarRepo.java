package com.codism.car.CarProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codism.car.CarProject.entity.Car;

public interface CarRepo extends JpaRepository<Car, Long>{
	

}
