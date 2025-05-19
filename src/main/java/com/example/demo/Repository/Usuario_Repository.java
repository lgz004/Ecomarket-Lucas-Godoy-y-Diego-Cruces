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

    public Usuario_Repository() {

    }

    public String obterUsuarios() {
        String output = "";

        for (Usuario_Model usuario : usuarios) {
            output+= "Nombre usuario: " + usuario.getNombre() + "\n";
            output+= "Contraseña: " + usuario.getPassword() + "\n";
            output+= "Correo: " + usuario.getEmail() + "\n";
        }
        if (usuarios.size() > 0) {
            return output;
        }else{
            return "No se encuentran usuarios";
        }
    }

    public String obtenerUsuarioId(int id) {

        for (Usuario_Model usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario.toString();
            }
        }
        return "No se encuentra usuario con ese nombre";
    }

    public String guardarUsuario(Usuario_Model usuario) {
        usuarios.add(usuario);
        return "Usuario agregado con exito";
    }

    public String actualizarUsuario(int id, Usuario_Model usuario) {
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

    public String eliminarUsuario(int id) {
        for (Usuario_Model usuario : usuarios) {
            if (usuario.getId() == id) {
                usuarios.remove(usuario);
                return "Eliminado con exito";
            }
        }
        return "No existe un usuario con ese id";
    }
}
