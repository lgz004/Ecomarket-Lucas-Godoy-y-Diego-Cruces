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
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Usuario_Model extends RepresentationModel<Usuario_Model> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private int idUsuario;
private String nombre;
private String email;
private String password;
}
