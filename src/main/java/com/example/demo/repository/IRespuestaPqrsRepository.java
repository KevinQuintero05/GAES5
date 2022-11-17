package com.example.demo.repository;

import com.example.demo.entity.RespuestaPqrs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRespuestaPqrsRepository extends JpaRepository<RespuestaPqrs,Long> {
}
