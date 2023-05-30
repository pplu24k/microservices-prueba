package com.pepe.userserv.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pepe.userserv.entity.Userr;
import com.pepe.userserv.feignclients.BikeFeignClient;
import com.pepe.userserv.feignclients.CarFeignClient;
import com.pepe.userserv.model.Bike;
import com.pepe.userserv.model.Car;
import com.pepe.userserv.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	CarFeignClient carFeignClient;
	
	@Autowired
	BikeFeignClient bikeFeignClient;
	
	public List<Userr> findAll(){
		return userRepository.findAll();
	}
	
	public Userr findUserById(int id) {
		return userRepository.findById(id).orElse(null);
	}
	
	public Userr save(Userr u) {
		Userr userNew = userRepository.save(u);
		return userNew;
	}
	
	public List<Car> getCars(int userId){
		List<Car> cars = restTemplate.getForObject("http://localhost:8002/car/user/" + userId, List.class);
		return cars;
	}
	
	public List<Bike> getBikes(int userId){
		List<Bike> cars = restTemplate.getForObject("http://localhost:8003/bike/user/" + userId, List.class);
		return cars;
	}
	
	public Car saveCar (int id, Car car) {
		car.setUserId(id);
		Car carNew = carFeignClient.saveCar(car);
		return carNew;
	}
	
	public Bike saveBike (int id, Bike bike) {
		bike.setUserId(id);
		Bike bikeNew = bikeFeignClient.saveBike(bike);
		return bikeNew;
	}
	public Map<String, Object> getUserAndVehicles(int id){
		Map<String,Object> result = new HashMap<>();
		Userr user = userRepository.findById(id).orElse(null);
		
		if(user == null) {
			result.put("Mensaje", "No existe el usuario");
			return result;
		}
		result.put("User",user);
		List<Car> cars = carFeignClient.getCars(id);
		if(cars.isEmpty())
			result.put("cars", "No tiene");
		else
			result.put("cars", cars);
		List<Bike> bikes = bikeFeignClient.getBikes(id);
		if(bikes.isEmpty())
			result.put("bikes", bikes);
		else
			result.put("bikes", bikes);
		
		
		
		return result;
		
	}
	

}
