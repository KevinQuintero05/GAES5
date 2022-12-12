package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "respuestaspqrs")
@Getter
@Setter
public class RespuestaPqrs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_respuesta", unique = true)
    private Long idrespuesta;

    @NotEmpty
    @Column(columnDefinition = "text")
    private String contenido;

    @NotNull (message = "Seleccione una Pqr a responder")
    @ManyToOne
    @JoinColumn(name = "noregistro")
    @JsonBackReference
    private Pqrs pqrs;
}
