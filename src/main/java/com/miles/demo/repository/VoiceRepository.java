package com.miles.demo.repository;

import com.miles.demo.bean.Voice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoiceRepository extends JpaRepository<Voice, Integer> {
}
