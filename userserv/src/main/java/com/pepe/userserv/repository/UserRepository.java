package com.pepe.userserv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pepe.userserv.entity.Userr;

@Repository
public interface UserRepository extends JpaRepository<Userr, Integer> {

}
