package com.pepe.carserv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pepe.carserv.entity.Car;
import com.pepe.carserv.repository.CarRepository;

@Service
public class CarService {
	
	@Autowired
	CarRepository carRepository;
	
	public List<Car> findAll(){
		return carRepository.findAll();
	}
	
	public Car findCarById(int id) {
		return carRepository.findById(id).orElse(null);
	}
	
	public Car save(Car u) {
		Car userNew = carRepository.save(u);
		return userNew;
	}
	
	public List<Car> findByUserId(int userId){
		return carRepository.findByUserId(userId);
	}
	

}
