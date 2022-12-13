package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Table(name = "conductor")
@Getter
@Setter
public class Conductor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="idConductor", unique = true)
    private Long idConductor;

    @NotEmpty
    private String categorialicencia;

    @NotEmpty
    private String Arl;

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String Cedula;

    @NotEmpty
    private String Eps;

    @NotEmpty
    private String Seguro;

    @NotEmpty
    private String Telefono;

    /* Super constructor*/

    public Conductor() {
    }

    public Conductor(Long idConductor, String categorialicencia, String arl, String nombre, String cedula, String eps, String seguro, String telefono) {
        this.idConductor = idConductor;
        this.categorialicencia = categorialicencia;
        Arl = arl;
        this.nombre = nombre;
        Cedula = cedula;
        Eps = eps;
        Seguro = seguro;
        Telefono = telefono;
    }
}

