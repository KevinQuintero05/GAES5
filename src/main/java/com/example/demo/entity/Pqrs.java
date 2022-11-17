package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pqrs")
@Getter
@Setter
public class Pqrs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "noregistro", unique = true)
    private Long noregistro;

    @Column(length = 50, nullable = false)
    private String grupointeres;

    @Column(length = 50, nullable = false)
    private String personainteres;

    @Column(length = 50, nullable = false)
    private String departamento;

    @Column(length = 50, nullable = false)
    private String ciudad;

    /*@Column(length = 50, nullable = false)
    private String servicioreservado;*/

    @Column(length = 50, nullable = false)
    private String asuntopqrs;

    @Column(length = 50, nullable = false)
    private String descripcion;

    @CreationTimestamp
    @Column(length = 50, updatable = false)
    private LocalDateTime fecharegistroPQRS;

    @Column(length = 50, nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "idusuario")
    @JsonBackReference
    private Usuario usuario;


}