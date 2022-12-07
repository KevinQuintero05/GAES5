package com.example.demo.repository;

import com.example.demo.entity.Tarifas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISolicitudesRepository extends JpaRepository<Tarifas, Long> {
}
