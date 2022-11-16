package com.example.demo.repository;

import com.example.demo.entity.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IservicioRepository extends JpaRepository<Servicio,Long> {
}
