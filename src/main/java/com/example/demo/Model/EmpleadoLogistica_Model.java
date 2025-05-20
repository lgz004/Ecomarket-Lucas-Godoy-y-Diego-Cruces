package com.example.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class EmpleadoLogistica_Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLogistica;
    private String nombre;
    private String email;
    private String password;
    private String tiendaAsignada;
}
