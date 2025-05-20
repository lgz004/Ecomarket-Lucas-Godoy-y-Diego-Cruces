package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GerenteTienda_Model extends Usuario_Model {

    private int idGerente;
    private String nombre;
    private String email;
    private String password;
    private String tiendaAsignada;

}