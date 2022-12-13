package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @NotEmpty
    @Column(length = 50, nullable = false)
    private String grupointeres;

    @NotEmpty
    @Column(length = 50, nullable = false)
    private String personainteres;

    @NotEmpty
    @Column(length = 50, nullable = false)
    private String ciudad;

    /*@Column(length = 50, nullable = false)
    private String servicioreservado;*/

    @NotEmpty
    @Column(length = 50, nullable = false)
    private String asuntopqrs;

    @NotEmpty
    @Column(columnDefinition = "text")
    private String descripcion;

    @CreationTimestamp
    @Column(length = 50, updatable = false)
    private LocalDateTime fecharegistroPQRS;


    @Column(length = 50, nullable = false)
    private String email;

    @Column(columnDefinition = "text")
    private String respuesta;

    @ManyToOne
    @JoinColumn(name = "idusuario")
    @JsonBackReference
    private Usuario usuario;

    @NotNull(message = "Debe seleccionar un servicio")
    @ManyToOne
    @JoinColumn(name = "idservicio")
    @JsonBackReference
    private Servicio servicio;


}