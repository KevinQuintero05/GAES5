package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @NotEmpty
    @Column(length = 50, nullable = false)
    private String ruta;

    @NotNull
    @Column(length = 50, nullable = false)
    private Long tarifaRuta;

    @NotEmpty
    @Column(length = 50, nullable = false)
    private String servicio;

    @NotNull
    @Column(length = 50, nullable = false)
    private Long tarifaServicio;

    @NotNull(message = "Debe de colocar un numero de reserva")
    @ManyToOne
    @JoinColumn(name = "noReserva")
    @JsonBackReference
    private Cronograma cronograma;
}
