package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "servicios")
@Getter
@Setter
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idServicio", unique = true)
    private Long idservicio;

    private String nomservicio;
    private String tipo;

    private String descripcion;

    /* Super constructor*/

    public Servicio() {
    }

    public Servicio(Long idservicio, String nomservicio, String tipo, String descripcion) {
        this.idservicio = idservicio;
        this.nomservicio = nomservicio;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }
}
