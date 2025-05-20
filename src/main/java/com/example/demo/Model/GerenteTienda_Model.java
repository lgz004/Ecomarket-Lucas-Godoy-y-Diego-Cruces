package com.example.demo.Model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GerenteTienda_Model extends Usuario_Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idGerente;
    private String nombre;
    private String email;
    private String password;
    private String tiendaAsignada;

}