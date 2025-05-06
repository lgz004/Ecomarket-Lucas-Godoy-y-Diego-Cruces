package com.example.demo.Repository;

import com.example.demo.Model.Usuario_Model;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class Usuario_Repository {
    private List<Usuario_Model> usuarios = new ArrayList<>();
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
        return "Agregado con exito";
    }

    public Usuario_Model actualizarUsuario(int id, Usuario_Model usuario) {
        int index = 0;
        for (Usuario_Model temp : usuarios) {
            if (temp.getId() == id) {
                index = usuarios.indexOf(temp);
                break;
            }
        }
        if (index != -1) {
            usuarios.set(index, usuario);
            return "Actualizado con exito";
        } else {
            return "No existe un usuario con ese nombre";
        }
    }

    public void eliminarUsuario(int id) {
        Usuario_Model Usuario_model = buscarUsuarioPorId(id);
        if (Usuario_model != null) {
            usuarios.remove(Usuario_model);
        }
    }
}
