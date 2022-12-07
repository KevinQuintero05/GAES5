package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "cronograma")
@Getter
@Setter
public class Cronograma {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "noReserva", unique = true)
    private Long noReserva;

    @CreationTimestamp
    @Column(length = 50, updatable = false)
    private Date fechaRegistro;

    @CreationTimestamp
    @Column(length = 50, updatable = false)
    private Date fechaPartida;

    @CreationTimestamp
    @Column(length = 50, updatable = false)
    private Date fechaLlegada;

    @Column(length = 50, nullable = false)
    private String ciudadDestino;

    @Column(length = 50, nullable = false)
    private String ciudadOrigen;

    @Column(length = 50, nullable = false)
    private String direccionOrigen;

    @Column(length = 50, nullable = false)
    private String direccionDestino;


    @ManyToOne
    @JoinColumn(name = "idConductor")
    @JsonBackReference
    private Conductor conductor;

    @ManyToOne
    @JoinColumn(name = "idservicio")
    @JsonBackReference
    private Servicio servicio;

    @ManyToOne
    @JoinColumn(name = "idSolicitudes")
    @JsonBackReference
    private Solicitudes solicitudes;

    @ManyToOne
    @JoinColumn(name = "idvehiculo")
    @JsonBackReference
    private Vehiculos vehiculos;

}
