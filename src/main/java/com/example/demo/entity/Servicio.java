package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "servicios")
@Getter
@Setter
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idServicio", unique = true)
    private Long idservicio;


    @NotEmpty(message = "Por favor ingresé el nombre del servicio.")
    @Column(length = 50, nullable = false)
    private String nomservicio;

    @NotEmpty(message = "Por favor ingresé el tipo.")
    @Column(length = 50, nullable = false)
    private String tipo;

    @NotEmpty(message = "Por favor ingresé la descripción.")
    @Column(length = 50, nullable = false)
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
