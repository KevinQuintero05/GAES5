package com.example.demo.repository;

import com.example.demo.entity.Solicitudes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISolicitudesRepository extends JpaRepository<Solicitudes, Long> {
}
