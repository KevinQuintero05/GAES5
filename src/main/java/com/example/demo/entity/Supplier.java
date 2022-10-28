package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "supplier")
@Getter
@Setter
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_supplier", unique = true)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 100, nullable = false)
    private String address;
    @Column(length = 50, nullable = false)
    private String phone;
    @Column(length = 50, nullable = false, name = "contact_name")
    private String contactName;

    public Supplier(Long id, String name, String address, String phone, String contactName) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.contactName = contactName;
    }

    public Supplier() {
    }
}
