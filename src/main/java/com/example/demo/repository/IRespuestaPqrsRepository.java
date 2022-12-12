package com.example.demo.repository;

import com.example.demo.entity.RespuestaPqrs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IRespuestaPqrsRepository extends JpaRepository<RespuestaPqrs,Long> {

}
