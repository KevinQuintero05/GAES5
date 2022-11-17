package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "users_sequence"
    )
    private Long idusuario;

    private String nombreusuario;

    private String nombres;

    private String apellidos;

    private String correoelectronico;

    private String password;

    private String estadocuenta;

    @Column(name = "nocedula", unique = true)
    private String nocedula;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime tiempoRegistro;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime tiempoActualizacion;

    @Enumerated(EnumType.STRING)
    private Role role;
    private String direccion;

    private int telefono;

    private int edad;



    public Usuario() {
    }
}
