package com.example.demo.repository;

import com.example.demo.entity.Pqrs;
import com.example.demo.entity.Valoracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IValoracionRepository extends JpaRepository<Valoracion, Long> {
    @Query("SELECT v FROM Valoracion v WHERE v.usuario.idusuario = :id")
    List<Valoracion> getValoracionByIdusuario(@Param("id")Long id);

}
