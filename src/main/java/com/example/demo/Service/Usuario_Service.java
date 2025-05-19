package com.example.demo.Service;

import com.example.demo.Model.Usuario_Model;
import com.example.demo.Repository.Usuario_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Usuario_Service {
    @Autowired
    private Usuario_Repository usuario_Repository;

    public String agregar_Usuario(Usuario_Model usuario){
        usuario_Repository.save(usuario);
        return "Usuario agregado con exito";
    }

    public String eliminar_Usuario(int id){
        if(usuario_Repository.existsById(id)){
            usuario_Repository.deleteById(id);
            return "Usuario eliminado con exito";
        }else {
            return "Usuario no encontrado";
        }
    }

    public String Listar_Usuarios(){
        String Output = "";
        for(Usuario_Model usuario : usuario_Repository.findAll()){
            Output += "Id: "+usuario.getNombre() + "\n";
            Output += "Nombre: "+usuario.getNombre() + "\n";
            Output += "Email: "+usuario.getEmail() + "\n";
            Output += "Password: "+usuario.getPassword() + "\n";
        }
        if (Output.isEmpty()){
            return "Usuario no encontrado";
        }else  {
            return Output;
        }
    }

    public String obtener_Usuario(int id){
        String Output = "";
        if(usuario_Repository.existsById(id)){
            Usuario_Model usuario = usuario_Repository.findById(id).get();
            Output += "Id: "+usuario.getNombre() + "\n";
            Output += "Nombre: "+usuario.getNombre() + "\n";
            Output += "Email: "+usuario.getEmail() + "\n";
            Output += "Password: "+usuario.getPassword() + "\n";
            return Output;
        }else  {
            return "Usuario no encontrado";
        }
    }

    public String actualizar_Usuario(int id, Usuario_Model usuario){
        if(usuario_Repository.existsById(id)){
            Usuario_Model usuariob = usuario_Repository.findById(id).get();
            usuario.setNombre(usuariob.getNombre());
            usuario.setEmail(usuariob.getEmail());
            usuario.setPassword(usuariob.getPassword());
            usuario_Repository.save(usuariob);
            return "Usuario actualizado con exito";
        }else  {
            return "Usuario no encontrado";
        }
    }
}
