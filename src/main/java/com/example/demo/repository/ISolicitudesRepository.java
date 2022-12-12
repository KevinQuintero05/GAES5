package com.example.demo.repository;

import com.example.demo.entity.Solicitudes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISolicitudesRepository extends JpaRepository<Solicitudes, Long> {
    @Query("SELECT s FROM Solicitudes s WHERE s.usuario.idusuario = :id")
    List<Solicitudes> getSolicitudesByIdusuario(@Param("id")Long id);
}

