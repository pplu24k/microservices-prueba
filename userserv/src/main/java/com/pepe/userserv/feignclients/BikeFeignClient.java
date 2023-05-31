package com.pepe.userserv.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pepe.userserv.model.Bike;
import com.pepe.userserv.model.Car;

@FeignClient(name = "bike-service")
public interface BikeFeignClient {

	@PostMapping("/bike")
	Bike saveBike(@RequestBody Bike bike);
	
	@GetMapping("/bike/user/{id}")
	List<Bike> getBikes(@PathVariable("id") int id);
	
	
}
