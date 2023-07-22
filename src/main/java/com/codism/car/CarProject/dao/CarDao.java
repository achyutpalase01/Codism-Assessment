package com.codism.car.CarProject.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.codism.car.CarProject.entity.Car;
import com.codism.car.CarProject.repository.CarRepo;

@Repository
public class CarDao {
	
	@Autowired
	private CarRepo carRepo;

	public List<Car> getCarDetail() {
		
		return carRepo.findAll();
	}

	public void saveCar(Car car) {
		
		carRepo.save(car);
	}

}
