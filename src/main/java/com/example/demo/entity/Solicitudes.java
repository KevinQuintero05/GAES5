package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name ="solicitudes")
@Getter
@Setter
public class Solicitudes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idSolicitudes", unique = true)
    private Long idSolicitudes;

    @Column(length = 50, nullable = false)
    private String estadoReserva;

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
    @JoinColumn(name = "idvehiculo")
    @JsonBackReference
    private Vehiculos vehiculos;

    @ManyToOne
    @JoinColumn(name = "idservicio")
    @JsonBackReference
    private Servicio servicio;

    @ManyToOne
    @JoinColumn(name = "idusuario")
    @JsonBackReference
    private Usuario usuario;
}

