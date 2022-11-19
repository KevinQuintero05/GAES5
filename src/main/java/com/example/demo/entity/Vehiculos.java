package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

@Entity
@Table(name = "vehiculos")
@Getter
@Setter
public class Vehiculos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idvehiculo", unique = true)
    private Long idvehiculo;

    @Column(length = 50, nullable = false)
    private String placa;

    @Column(length = 50, nullable = false)
    private String tipovehiculo;

    @Column(length = 50, nullable = false)
    private String capacidad;

    @Column(length = 50, nullable = false)
    private String marca;


    @Column(length = 50, nullable = false)
    private String soat;

    @Column(length = 50, nullable = false)
    private String modelo;


    @Column(length = 50, updatable = false)
    private String descripcion;

    @Column(length = 50, nullable = false)
    private String estadovehiculo;


    @ManyToOne
    @JoinColumn(name = "idservicio")
    @JsonBackReference
    private Servicio servicio;


}

