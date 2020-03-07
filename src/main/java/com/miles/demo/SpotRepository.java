package com.miles.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SpotRepository extends JpaRepository<Spot, Integer> {

}
