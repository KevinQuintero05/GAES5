package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "vehiculos")
@Getter
@Setter
public class Vehiculos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idvehiculo", unique = true)
    private Long idvehiculo;

    @NotEmpty(message = "Por favor ingresé la placa")
    @Column(length = 50, nullable = false)
    private String placa;

    @NotEmpty(message = "Por favor ingresé el tipo de vehículo")
    @Column(length = 50, nullable = false)
    private String tipovehiculo;

    @NotEmpty(message = "Por favor ingresé la capacidad")
    @Column(length = 50, nullable = false)
    private String capacidad;

    @NotEmpty(message = "Por favor ingresé la marca")
    @Column(length = 50, nullable = false)
    private String marca;

    @NotEmpty(message = "Por favor ingresé el soat")
    @Column(length = 50, nullable = false)
    private String soat;

    @NotEmpty(message = "Por favor ingresé el modelo")
    @Column(length = 50, nullable = false)
    private String modelo;

    @NotEmpty(message = "Por favor ingresé la descripción")
    @Column(columnDefinition = "text")
    private String descripcion;

    @NotEmpty(message = "Por favor ingresé el estado del vehículo")
    @Column(length = 50, nullable = false)
    private String estadovehiculo;


    @NotNull(message = "Debe seleccionar un servicio")
    @ManyToOne
    @JoinColumn(name = "idservicio")
    @JsonBackReference
    private Servicio servicio;


}

