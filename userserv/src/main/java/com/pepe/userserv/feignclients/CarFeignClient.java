package com.pepe.userserv.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pepe.userserv.model.Car;

@FeignClient(name = "car-service", url = "http://localhost:8002/car")

public interface CarFeignClient {
	
	@PostMapping()
	Car saveCar(@RequestBody Car car);
	
	@GetMapping("/user/{id}")
	List<Car> getCars(@PathVariable("id") int id);
	

}
