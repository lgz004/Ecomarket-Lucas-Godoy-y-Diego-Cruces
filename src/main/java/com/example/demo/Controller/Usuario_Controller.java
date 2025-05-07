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
    private Usuario_Service usuarioService;

    @GetMapping
    public String ListarUsuarios() {
        return usuarioService.getUsuarios();
    }

    @PostMapping
    public String agregarUsuario(@RequestBody Usuario_Model usuario) {
        return usuarioService.SaveUsuario(usuario);
    }

    @GetMapping("/{id}")
    public String buscarUsuarioPorId(@PathVariable int id) {
        return usuarioService.getUsuario(id);
    }

    @PutMapping("/{id}")
    public String atualizarUsuario(@PathVariable int id, @RequestBody Usuario_Model usuario) {
        return usuarioService.updateUsuario(id, usuario);
    }

    @DeleteMapping("/{id}")
    public String eliminarUsuario(@PathVariable int id) {
        return usuarioService.deleteUsuario(id);
    }
}
