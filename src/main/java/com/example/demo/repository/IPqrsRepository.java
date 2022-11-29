package com.example.demo.repository;

import com.example.demo.entity.Pqrs;
import com.example.demo.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPqrsRepository extends JpaRepository<Pqrs, Long> {
    @Query(value = "SELECT * FROM pqrs  WHERE idusuario = idusuario",nativeQuery = true)
    List<Pqrs> getPqrsByIdusuario(@Param("idusuario")long idusuario);


}
