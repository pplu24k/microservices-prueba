package com.pepe.userserv.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
	@GetMapping("/cars/{userId}")
	public ResponseEntity<List<Car>> getCars(@PathVariable("userId") int userId){
		
		
		Userr user = userService.findUserById(userId);
		if(user == null)
			return ResponseEntity.notFound().build();
	
		List<Car> cars = userService.getCars(userId);
		
		return ResponseEntity.ok(cars);
	}
	
	@GetMapping("/bikes/{userId}")
	public ResponseEntity<List<Bike>> getBikes(@PathVariable("userId") int userId){
		
		
		Userr user = userService.findUserById(userId);
		if(user == null)
			return ResponseEntity.notFound().build();
	
		List<Bike> bikes = userService.getBikes(userId);
		
		return ResponseEntity.ok(bikes);
	}
	
	@PostMapping("/savecar/{userId}")
	public ResponseEntity<Car> saveCar(@PathVariable("userId") int id, @RequestBody Car car){
		
		Car carNew = userService.saveCar(id, car);

		return ResponseEntity.ok(carNew);
		
	}
	
	@PostMapping("/savebike/{userId}")
	public ResponseEntity<Bike> saveBike(@PathVariable("userId") int id, @RequestBody Bike bike){
		
		Bike bikeNew = userService.saveBike(id, bike);

		return ResponseEntity.ok(bikeNew);
		
	}
	
	@GetMapping("/getAll/{id}")
	public ResponseEntity<Map<String,Object>> getAll(@PathVariable("id") int id){
		
		Map<String,Object> result = userService.getUserAndVehicles(id);
		
		return ResponseEntity.ok(result);
	}

}
