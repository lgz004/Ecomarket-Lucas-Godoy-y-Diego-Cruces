package com.example.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class GerenteTienda_Model extends RepresentationModel<GerenteTienda_Model> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idGerente;
    private String nombre;
    private String email;
    private String password;
    private String tiendaAsignada;

}