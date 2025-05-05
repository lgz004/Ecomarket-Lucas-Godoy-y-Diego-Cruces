package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Cliente_Model extends Usuario_Model {
    private String idCliente;
    private String direccionEnvio;

}
