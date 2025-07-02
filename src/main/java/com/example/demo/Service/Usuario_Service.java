package com.example.demo.Service;

import com.example.demo.Model.Usuario_Model;
import com.example.demo.Repository.Usuario_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Usuario_Service {
    @Autowired
    Usuario_Repository usuario_Repository;

    public void agregar_Usuario(Usuario_Model usuario){
        usuario_Repository.save(usuario);
    }

    public void eliminar_Usuario(int id){
       usuario_Repository.deleteById(id);
    }

    public List<Usuario_Model> Listar_Usuarios(){
        return usuario_Repository.findAll();
    }

    public Optional<Usuario_Model> obtener_Usuario(int id){
        return usuario_Repository.findById(id);
    }

    public void actualizar_Usuario(int id, Usuario_Model usuario){
        Usuario_Model usuario_actual = usuario_Repository.findById(id).get();
        usuario_actual.setNombre(usuario.getNombre());
        usuario_actual.setEmail(usuario.getEmail());
        usuario_actual.setPassword(usuario.getPassword());
        usuario_Repository.save(usuario_actual);
    }
}
