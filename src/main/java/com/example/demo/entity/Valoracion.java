package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "valoracionservicios")
@Getter
@Setter
public class Valoracion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idvaloracionservicio", unique = true)
    private Long idvaloracionservicio;

    @NotEmpty
    @Column(length = 50, nullable = false)
    private String puntuacion;

    @NotEmpty
    @Column(columnDefinition = "text")
    private String comentario;

    @NotNull(message = "Debe seleccionar un servicio")
    @ManyToOne
    @JoinColumn(name = "idservicio")
    @JsonBackReference
    private Servicio servicio;

    @ManyToOne
    @JoinColumn(name = "idusuario")
    @JsonBackReference
    private Usuario usuario;

}
