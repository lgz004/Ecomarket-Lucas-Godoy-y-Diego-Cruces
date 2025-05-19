package com.example.demo.Controller;

import com.example.demo.Model.Usuario_Model;
import com.example.demo.Service.Usuario_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class Usuario_Controller {
    @Autowired
    private Usuario_Service usuario_service;

    @GetMapping
    public String getUsuarios() {
        return usuario_service.Listar_Usuarios();
    }

    @PostMapping
    public String addUsuario(@RequestBody Usuario_Model usuario) {
        return usuario_service.agregar_Usuario(usuario);
    }

    @GetMapping("/{id}")
    public String getUsuarioById(@PathVariable int id) {
        return usuario_service.obtener_Usuario(id);
    }

    @DeleteMapping("/{id}")
    public String eliminarUsuario(@PathVariable int id) {
        return usuario_service.eliminar_Usuario(id);
    }

    @PutMapping("/{id}")
    public String editarUsuario(@PathVariable int id, @RequestBody Usuario_Model usuario) {
        return usuario_service.actualizar_Usuario(id, usuario);
    }
}
