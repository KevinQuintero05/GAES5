package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class Usuario implements UserDetails {
    @SequenceGenerator(
            name = "users_sequence",
            sequenceName = "users_sequence",
            allocationSize = 1
    )

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "users_sequence"
    )
    private Long idusuario;

    private String nombreusuario;

    private String nombres;

    private String apellidos;

    @NotNull(message = "Email cannot be empty")
    @Email(message = "Please enter a valid email address")
    @Column(name = "email", unique = true)
    private String email;

    @NotNull(message = "Password cannot be empty")
    @Length(min = 7, message = "Password should be atleast 7 characters long")
    @Column(name = "password")
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

    private String telefono;

    private String edad;

    @Column(name = "locked")
    private Boolean locked = false;

    @Column(name = "enabled")
    private Boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Role getRole() { return role; }

    public void setRole(com.example.demo.entity.Role role) {
        this.role = role;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getTelefono() {return telefono; }

    public void setTelefono(String telefono) {this.telefono = telefono; }


    public String getNocedula() {return  nocedula; }

    public void  setNocedula(String nocedula) {this.nocedula = nocedula; }

}
