package com.example.demo.Service;

import com.example.demo.Model.AdministradorSistema_Model;
import com.example.demo.Model.EmpleadoLogistica_Model;
import com.example.demo.Repository.AdministradorSistema_Repository;
import com.example.demo.Repository.EmpleadoLogistica_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministradorSistema_Service {
    @Autowired
    AdministradorSistema_Repository administradorSistemaRepository;

    public String agregar_AdministradorSistema(AdministradorSistema_Model administradorSistema){
        administradorSistemaRepository.save(administradorSistema);
        return "Administrador de Sistema agregado con exito";
    }

    public String eliminar_AdministradorSistema(int id){
        if(administradorSistemaRepository.existsById(id)){
            administradorSistemaRepository.deleteById(id);
            return "Administrador de Sistema eliminado con exito";
        }else {
            return "Administrador de sistema no encontrado";
        }
    }

    public String Listar_AdministradorSistema(){
        String Output = "";
        for(AdministradorSistema_Model empleadoLog : administradorSistemaRepository.findAll()){
            Output += "Id: "+empleadoLog.getIdAdmin() + "\n";
            Output += "Nombre: "+empleadoLog.getNombre() + "\n";
            Output += "Email: "+empleadoLog.getEmail() + "\n";
            Output += "Password: "+empleadoLog.getPassword() + "\n";
            Output += "Tienda: "+empleadoLog.getTienda() + "\n";
        }
        if (Output.isEmpty()){
            return "No hay Administradores de Sistema";
        }else  {
            return Output;
        }
    }

    public String obtener_AdministradorSistemaId(int id){
        String Output = "";
        if(administradorSistemaRepository.existsById(id)){
            AdministradorSistema_Model empleadoLog = administradorSistemaRepository.findById(id).get();
            Output += "Id: "+empleadoLog.getIdAdmin() + "\n";
            Output += "Nombre: "+empleadoLog.getNombre() + "\n";
            Output += "Email: "+empleadoLog.getEmail() + "\n";
            Output += "Password: "+empleadoLog.getPassword() + "\n";
            Output += "Tienda: "+empleadoLog.getTienda() + "\n";
            return Output;
        }else  {
            return "Administrador de Sistema no encontrado";
        }
    }

    public String actualizar_AdministradorSistema(int id, AdministradorSistema_Model AdministradorSistema){
        if(administradorSistemaRepository.existsById(id)){
            AdministradorSistema_Model AdministradorSistemaB = administradorSistemaRepository.findById(id).get();
            AdministradorSistemaB.setNombre(AdministradorSistema.getNombre());
            AdministradorSistemaB.setEmail(AdministradorSistema.getEmail());
            AdministradorSistemaB.setPassword(AdministradorSistema.getPassword());
            AdministradorSistemaB.setTienda(AdministradorSistema.getTienda());
            administradorSistemaRepository.save(AdministradorSistemaB);
            return "Administrador de Sistema actualizado con exito";
        }else  {
            return "Administrador de Sistema no encontrado";
        }
    }
}
