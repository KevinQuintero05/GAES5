package com.example.demo.repository;

import com.example.demo.entity.Valoracion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IValoracionRepository extends JpaRepository<Valoracion, Long> {
}
