package com.example.demo.repository;

import com.example.demo.entity.Cronograma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICronogramaRepository extends JpaRepository<Cronograma, Long> {
}
