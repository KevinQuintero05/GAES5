package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
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

    private String categoria_licencia;

    private String Arl;

    private String Cedula;

    private String Eps;

    private String Seguro;

    private String Telefono;




    /* Super constructor*/

    public Conductor() {
    }

    public Conductor(Long idConductor, String categoria_licencia, String Cedula, String Telefono, String Seguro, String Eps, String Arl) {
        this.idConductor = idConductor;
        this.categoria_licencia = categoria_licencia;
        this.Arl = Arl;
        this.Cedula = Cedula;
        this.Eps = Eps;
        this.Seguro = Seguro;
        this.Telefono = Telefono;



    }
}

