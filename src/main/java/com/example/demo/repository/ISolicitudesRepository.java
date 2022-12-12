package com.example.demo.repository;

import com.example.demo.entity.Solicitudes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISolicitudesRepository extends JpaRepository<Solicitudes, Long> {
    @Query(value = "SELECT * FROM solicitudes  WHERE idusuario = idusuario",nativeQuery = true)
    List<Solicitudes> getSolicitudByIdusuario(@Param("idusuario")long idusuario);
}
