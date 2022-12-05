package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "tarifas")
@Getter
@Setter
public class Tarifas {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idTarifa", unique = true)
    private Long idTarifa;

    @Column(length = 50, nullable = false)
    private String ruta;

    @Column(length = 50, nullable = false)
    private Long tarifaRuta;

    @Column(length = 50, nullable = false)
    private String servicio;

    @Column(length = 50, nullable = false)
    private Long tarifaServicio;


    @ManyToOne
    @JoinColumn(name = "noReserva")
    @JsonBackReference
    private Cronograma cronograma;
}
