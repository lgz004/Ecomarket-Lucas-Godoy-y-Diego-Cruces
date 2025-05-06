package com.example.demo.Service;

import com.example.demo.Model.Usuario_Model;
import com.example.demo.Repository.Usuario_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Usuario_Service {
    @Autowired
    private Usuario_Repository Usuario_Repository;

    public List<Usuario_Model> getUsuarios() {
        return Usuario_Repository.getUsuarios();
    }

    public Usuario_Model SaveUsuario(Usuario_Model Usuario_Model) {
        return Usuario_Repository.guardarUsuario(Usuario_Model);
    }

    public Usuario_Model getUsuario(int id) {
        return Usuario_Repository.buscarUsuarioPorId(id);
    }

    public Usuario_Model updateUsuario(Usuario_Model Usuario_Model) {
        return Usuario_Repository.actualizarUsuario(Usuario_Model);
    }

    public Usuario_Model deleteUsuario(int id) {
        Usuario_Repository.eliminarUsuario(id);
        return "Usuario eliminado";
    }

}
