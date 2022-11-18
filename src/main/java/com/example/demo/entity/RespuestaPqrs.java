package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "respuestaspqrs")
@Getter
@Setter
public class RespuestaPqrs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_respuesta", unique = true)
    private Long idrespuesta;

    @Column(columnDefinition = "text")
    private String contenido;

    @ManyToOne
    @JoinColumn(name = "noregistro")
    @JsonBackReference
    private Pqrs pqrs;
}
