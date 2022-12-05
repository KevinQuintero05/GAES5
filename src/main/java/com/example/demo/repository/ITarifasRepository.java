package com.example.demo.repository;

import com.example.demo.entity.Tarifas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITarifasRepository extends JpaRepository<Tarifas, Long> {
}
