package com.example.demo.Repository;

import com.example.demo.Model.Usuario_Model;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class Usuario_Repository {
    private List<Usuario_Model> usuarios; = new ArrayList<>();
    public List<Usuario_Model> getUsuarios() {
        return usuarios;
    }

    public Usuario_Model buscarUsuarioPorId(int id) {
        for (Usuario_Model usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null;
    }

    public List<Usuario_Model> obterUsuarios() {
        return usuarios;
    }

    public Usuario_Model guardarUsuario(Usuario_Model usuario) {
        usuarios.add(usuario);
        return usuario;
    }

    public Usuario_Model actualizarUsuario(Usuario_Model usuario) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId() == usuario.getId()) {
                usuarios.set(i, usuario);
                return usuario;
            }
        }
        return null;
    }

    public void eliminarUsuario(int id) {
        Usuario_Model Usuario_model = buscarUsuarioPorId(id);
        if (Usuario_model != null) {
            usuarios.remove(Usuario_model);
        }
    }


}
