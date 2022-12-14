package com.example.demo.repository;

import com.example.demo.entity.RespuestaPqrs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IRespuestaPqrsRepository extends JpaRepository<RespuestaPqrs,Long> {
    @Query("SELECT rp FROM RespuestaPqrs rp WHERE rp.pqrs.usuario.idusuario = :id")
    List<RespuestaPqrs> getRespuestaByidusuario(@Param("id")Long id);
}
