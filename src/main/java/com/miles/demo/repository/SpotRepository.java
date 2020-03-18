package com.miles.demo.repository;

import com.miles.demo.bean.Spot;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SpotRepository extends JpaRepository<Spot, Integer> {

}
