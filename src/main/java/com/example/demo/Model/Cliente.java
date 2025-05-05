package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Cliente extends Usuario {
    private String idCliente;
    private String direccionEnvio;

}
