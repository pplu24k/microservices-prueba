package com.pepe.userserv.controller;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pepe.userserv.entity.Userr;
import com.pepe.userserv.model.Bike;
import com.pepe.userserv.model.Car;
import com.pepe.userserv.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public ResponseEntity<List<Userr>> getAll(){
		
		List<Userr> users = userService.findAll();
		if(users.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(users);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Userr> getById(@PathVariable("id") int id){
		
		Userr user = userService.findUserById(id);
		if(user == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(user);
		
	}
	
	
	@PostMapping()
	public ResponseEntity<Userr> getById(@RequestBody Userr user){
		
		Userr userNew = userService.save(user);

		return ResponseEntity.ok(userNew);
		
	}
	@CircuitBreaker(name = "carsCB", fallbackMethod = "fallBackGetCars")
	@GetMapping("/cars/{userId}")
	public ResponseEntity<List<Car>> getCars(@PathVariable("userId") int userId){
		
		
		Userr user = userService.findUserById(userId);
		if(user == null)
			return ResponseEntity.notFound().build();
	
		List<Car> cars = userService.getCars(userId);
		
		return ResponseEntity.ok(cars);
	}
	
	@CircuitBreaker(name = "carsCB", fallbackMethod = "fallBackSaveCar")
	@PostMapping("/savecar/{userId}")
	public ResponseEntity<Car> saveCar(@PathVariable("userId") int id, @RequestBody Car car){
		
		Car carNew = userService.saveCar(id, car);

		return ResponseEntity.ok(carNew);
		
	}
	
	@CircuitBreaker(name = "bikesCB", fallbackMethod = "fallBackGetBikes")
	@GetMapping("/bikes/{userId}")
	public ResponseEntity<List<Bike>> getBikes(@PathVariable("userId") int userId){
		
		
		Userr user = userService.findUserById(userId);
		if(user == null)
			return ResponseEntity.notFound().build();
	
		List<Bike> bikes = userService.getBikes(userId);
		
		return ResponseEntity.ok(bikes);
	}

	@CircuitBreaker(name = "bikesCB", fallbackMethod = "fallBackSaveBike")
	@PostMapping("/savebike/{userId}")
	public ResponseEntity<Bike> saveBike(@PathVariable("userId") int id, @RequestBody Bike bike){
		
		Bike bikeNew = userService.saveBike(id, bike);

		return ResponseEntity.ok(bikeNew);
		
	}
	
	@CircuitBreaker(name = "allCB", fallbackMethod = "fallBackGetAll")
	@GetMapping("/getAll/{id}")
	public ResponseEntity<Map<String,Object>> getAll(@PathVariable("id") int id){
		
		Map<String,Object> result = userService.getUserAndVehicles(id);
		
		return ResponseEntity.ok(result);
	}
	
	private ResponseEntity<List<Car>> fallBackGetCars(@PathVariable("userId") int userId, RuntimeException e){
		return new ResponseEntity("El usuario " + userId + " no puede acceder a sus coches actualmente", HttpStatus.OK);
	}
	
	private ResponseEntity<Car> fallBackSaveCar(@PathVariable("userId") int id, @RequestBody Car car, RuntimeException e){
		return new ResponseEntity("El usuario " + id + " no puede comprar el coche", HttpStatus.OK);
	}
	private ResponseEntity<List<Bike>> fallBackGetBikes(@PathVariable("userId") int userId, RuntimeException e){
		return new ResponseEntity("El usuario " + userId + " no puede acceder a sus motos actualmente", HttpStatus.OK);
	}
	
	private ResponseEntity<Bike> fallBackSaveBike(@PathVariable("userId") int id, @RequestBody Bike bike, RuntimeException e){
		return new ResponseEntity("El usuario " + id + " no puede comprar la moto", HttpStatus.OK);
	}
	

	public ResponseEntity<Map<String,Object>> fallBackGetAll(@PathVariable("id") int id, RuntimeException e){
		return new ResponseEntity("El usuario " + id + " no puede acceder a sus vehiculos actualmente", HttpStatus.OK);
	}
	

}
