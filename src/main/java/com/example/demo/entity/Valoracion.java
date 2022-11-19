package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "valoracionservicios")
@Getter
@Setter
public class Valoracion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idvaloracionservicio", unique = true)
    private Long idvaloracionservicio;

    @Column(length = 50, nullable = false)
    private String puntuacion;

    @Column(columnDefinition = "text")
    private String comentario;


    @ManyToOne
    @JoinColumn(name = "idservicio")
    @JsonBackReference
    private Servicio servicio;

    @ManyToOne
    @JoinColumn(name = "idusuario")
    @JsonBackReference
    private Usuario usuario;

}
