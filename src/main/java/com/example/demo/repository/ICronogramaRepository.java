package com.example.demo.repository;

import com.example.demo.entity.Cronograma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICronogramaRepository extends JpaRepository<Cronograma, Long> {
    @Query(value ="SELECT * FROM cronograma  WHERE idusuario = idusuario",nativeQuery = true)
    List<Cronograma> getCronogramaByIdsuario(@Param("idsuario")long idusuario);
}
