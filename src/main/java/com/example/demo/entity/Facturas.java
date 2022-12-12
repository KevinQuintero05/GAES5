package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "facturas")
@Getter
@Setter
public class Facturas {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idFactura", unique = true)
    private Long idFactura;

    @Column(length = 50, nullable = false)
    private String ruta;

    @Column(length = 50, nullable = false)
    private Long tarifaRuta;

    @Column(length = 50, nullable = false)
    private String servicio;

    @Column(length = 50, nullable = false)
    private Long tarifaServicio;

    @Column(length = 50, nullable = false)
    private Long costoTotal;


    @ManyToOne
    @JoinColumn(name = "noReserva")
    @JsonBackReference
    private Cronograma cronograma;
}
