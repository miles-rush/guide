package com.miles.demo.repository;

import com.miles.demo.bean.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, Integer> {
}
