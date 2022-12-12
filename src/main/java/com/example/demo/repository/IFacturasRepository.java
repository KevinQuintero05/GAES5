package com.example.demo.repository;

import com.example.demo.entity.Facturas;
import com.example.demo.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFacturasRepository extends JpaRepository<Facturas, Long> {
    @Query("SELECT f FROM Facturas f WHERE f.usuario.idusuario = :id")
    List<Facturas> getFacturasByIdusuario(@Param("id")Long id);


}