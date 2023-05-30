package com.pepe.carserv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pepe.carserv.entity.Car;
import com.pepe.carserv.service.CarService;

@RestController
@RequestMapping("/car")
public class CarController {
	
	@Autowired
	CarService carService;
	
	@GetMapping
	public ResponseEntity<List<Car>> getAll(){
		
		List<Car> cars = carService.findAll();
		if(cars.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(cars);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Car> getById(@PathVariable("id") int id){
		
		Car car = carService.findCarById(id);
		if(car == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(car);
		
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<List<Car>> getByUserId(@PathVariable("id") int id){
		
		List<Car> cars = carService.findByUserId(id);
		if(cars == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(cars);
		
	}
	
	
	@PostMapping()
	public ResponseEntity<Car> saveCar(@RequestBody Car car){
		
		Car carNew = carService.save(car);

		return ResponseEntity.ok(carNew);
		
	}

}
