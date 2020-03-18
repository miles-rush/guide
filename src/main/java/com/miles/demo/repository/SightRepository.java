package com.miles.demo.repository;

import com.miles.demo.bean.Sight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SightRepository extends JpaRepository<Sight, Integer> {
}
