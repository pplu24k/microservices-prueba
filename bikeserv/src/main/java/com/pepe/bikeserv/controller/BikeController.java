package com.pepe.bikeserv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pepe.bikeserv.entity.Bike;
import com.pepe.bikeserv.service.BikeService;

@RestController
@RequestMapping("/bike")
public class BikeController {
	
	@Autowired
	BikeService bikeService;
	
	@GetMapping
	public ResponseEntity<List<Bike>> getAll(){
		
		List<Bike> cars = bikeService.findAll();
		if(cars.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(cars);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Bike> getById(@PathVariable("id") int id){
		
		Bike bike = bikeService.findBikeById(id);
		if(bike == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(bike);
		
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<List<Bike>> getByUserId(@PathVariable("id") int id){
		
		List<Bike> cars = bikeService.findByUserId(id);
		if(cars == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(cars);
		
	}
	
	
	@PostMapping()
	public ResponseEntity<Bike> saveCar(@RequestBody Bike car){
		
		Bike carNew = bikeService.save(car);

		return ResponseEntity.ok(carNew);
		
	}

}
