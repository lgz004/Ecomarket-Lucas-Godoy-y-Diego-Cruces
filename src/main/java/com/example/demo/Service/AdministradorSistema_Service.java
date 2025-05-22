package com.example.demo.Service;

import com.example.demo.Model.AdministradorSistema_Model;
import com.example.demo.Repository.AdministradorSistema_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministradorSistema_Service {

    @Autowired
    AdministradorSistema_Repository administradorSistema_Repository;

    public String agregar_AdministradorSistema(AdministradorSistema_Model administradorSistema){
        administradorSistema_Repository.save(administradorSistema);
        return "Administrador de Sistema agregado con exito";
    }

    public String eliminar_AdministradorSistema(int id){
        if(administradorSistema_Repository.existsById(id)){
            administradorSistema_Repository.deleteById(id);
            return "Administrador de Sistema eliminado con exito";
        }else {
            return "Administrador de Sistema no encontrado";
        }
    }

    public String Listar_AdministradoresSistema(){
        String Output = "";
        for(AdministradorSistema_Model administadorSistema : administradorSistema_Repository.findAll()){
            Output += "Id: "+administadorSistema.getIdAdmin() + "\n";
            Output += "Nombre: "+administadorSistema.getNombre() + "\n";
            Output += "Email: "+administadorSistema.getEmail() + "\n";
            Output += "Password: "+administadorSistema.getPassword() + "\n";
            Output += "Tienda: "+administadorSistema.getTienda() + "\n";
        }
        if (Output.isEmpty()){
            return "No hay Administradores de Sistema";
        }else  {
            return Output;
        }
    }

    public String obtener_AdministradorSistemaId(int id){
        String Output = "";
        if(administradorSistema_Repository.existsById(id)){
            AdministradorSistema_Model administradorSistema = administradorSistema_Repository.findById(id).get();
            Output += "Id: "+administradorSistema.getIdAdmin() + "\n";
            Output += "Nombre: "+administradorSistema.getNombre() + "\n";
            Output += "Email: "+administradorSistema.getEmail() + "\n";
            Output += "Password: "+administradorSistema.getPassword() + "\n";
            Output += "Tienda: "+administradorSistema.getTienda() + "\n";
            return Output;
        }else  {
            return "Administrador de Sistema no encontrado";
        }
    }

    public String actualizar_AdministradorSistema(int id, AdministradorSistema_Model administradorSistema){
        if(administradorSistema_Repository.existsById(id)){
            AdministradorSistema_Model administradorSistemaB = administradorSistema_Repository.findById(id).get();
            administradorSistemaB.setNombre(administradorSistema.getNombre());
            administradorSistemaB.setEmail(administradorSistema.getEmail());
            administradorSistemaB.setPassword(administradorSistema.getPassword());
            administradorSistema_Repository.save(administradorSistemaB);
            return "Administrador de Sistema actualizado con exito";
        }else  {
            return "Administrador de Sistema no encontrado";
        }
    }
}
