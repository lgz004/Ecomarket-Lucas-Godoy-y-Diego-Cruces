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

    public String getUsuarios() {
        return Usuario_Repository.obterUsuarios();
    }

    public String SaveUsuario(Usuario_Model Usuario_Model) {
        return Usuario_Repository.guardarUsuario(Usuario_Model);
    }

    public String getUsuario(int id) {
        return Usuario_Repository.obtenerUsuarioId(id);
    }

    public String updateUsuario(int id, Usuario_Model Usuario_Model) {
        return Usuario_Repository.actualizarUsuario(id, Usuario_Model);
    }

    public String deleteUsuario(int id) {
        return Usuario_Repository.eliminarUsuario(id);
    }

}
