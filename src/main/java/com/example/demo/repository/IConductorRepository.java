package com.example.demo.repository;

import com.example.demo.entity.Conductor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IConductorRepository extends JpaRepository<Conductor, Long> {
}
