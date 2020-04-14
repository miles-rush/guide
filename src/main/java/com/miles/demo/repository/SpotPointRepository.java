package com.miles.demo.repository;

import com.miles.demo.bean.Point;
import com.miles.demo.bean.SpotPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpotPointRepository extends JpaRepository<SpotPoint, Integer> {
}
