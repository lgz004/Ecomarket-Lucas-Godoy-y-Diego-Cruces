package com.example.demo.Controller;

import com.example.demo.Model.Usuario_Model;
import com.example.demo.Service.Usuario_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/GitHub/Ecomarket-Lucas-Godoy-y-Diego-Cruces")
public class Usuario_Controller {
    @Autowired
    private Usuario_Service usuarioService;

    @GetMapping
    public List<Usuario_Model> ListarUsuarios() {
        return usuarioService.getUsuarios();
    }

    @PostMapping
    public Usuario_Model agregarUsuario(@RequestBody Usuario_Model usuario) {
        return usuarioService.SaveUsuario(usuario);
    }

    @GetMapping("{id}")
    public Usuario_Model buscarUsuarioPorId(@PathVariable int id) {
        return usuarioService.getUsuario(id);
    }

    @PutMapping("{id}")
    public Usuario_Model atualizarUsuario(@PathVariable int id, @RequestBody Usuario_Model usuario) {
        return usuarioService.updateUsuario(usuario);
    }

    @DeleteMapping("{id}")
    public void eliminarUsuario(@PathVariable int id) {
        usuarioService.deleteUsuario(id);
    }
}
