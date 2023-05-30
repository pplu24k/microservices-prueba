package com.pepe.bikeserv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pepe.bikeserv.entity.Bike;
import com.pepe.bikeserv.repository.BikeRepository;

@Service
public class BikeService {
	
	@Autowired
	BikeRepository bikeRepository;
	
	public List<Bike> findAll(){
		return bikeRepository.findAll();
	}
	
	public Bike findBikeById(int id) {
		return bikeRepository.findById(id).orElse(null);
	}
	
	public Bike save(Bike u) {
		Bike bikeNew = bikeRepository.save(u);
		return bikeNew;
	}
	
	public List<Bike> findByUserId(int userId){
		return bikeRepository.findByUserId(userId);
	}
	

}
