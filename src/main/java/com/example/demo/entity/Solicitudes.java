package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;


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

    @NotNull(message = "Debe agregar una fecha de Partida")
    @FutureOrPresent
   // @DateTimeFormat(pattern = "yyyy-MM-dd")
    //@Column(length = 50, updatable = false)
    private Date fechaPartida;

    @NotNull(message = "Debe agregar una fecha de LLegada")
    @FutureOrPresent
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    //@Column(length = 50, updatable = false)
    private Date fechaLlegada;

    @NotEmpty
    @Column(length = 50, nullable = false)
    private String ciudadDestino;

    @NotEmpty
    @Column(length = 50, nullable = false)
    private String ciudadOrigen;

    @NotEmpty
    @Column(length = 50, nullable = false)
    private String direccionOrigen;

    @NotEmpty
    @Column(length = 50, nullable = false)
    private String direccionDestino;

    @NotNull(message = "Debe de Asignar un Vehículo")
    @ManyToOne
    @JoinColumn(name = "idvehiculo")
    @JsonBackReference
    private Vehiculos vehiculos;

    @NotNull (message = "Debe de seleccionar un servicio")
    @ManyToOne
    @JoinColumn(name = "idservicio")
    @JsonBackReference
    private Servicio servicio;

    @ManyToOne
    @JoinColumn(name = "idusuario")
    @JsonBackReference
    private Usuario usuario;
}

