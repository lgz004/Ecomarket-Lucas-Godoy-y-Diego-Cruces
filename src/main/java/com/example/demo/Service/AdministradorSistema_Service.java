package com.example.demo.Service;

import com.example.demo.Model.AdministradorSistema_Model;
import com.example.demo.Repository.AdministradorSistema_Repository;
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
        for(AdministradorSistema_Model adminSistema : administradorSistemaRepository.findAll()){
            Output += "Id: "+adminSistema.getIdAdmin() + "\n";
            Output += "Nombre: "+adminSistema.getNombre() + "\n";
            Output += "Email: "+adminSistema.getEmail() + "\n";
            Output += "Password: "+adminSistema.getPassword() + "\n";
            Output += "Tienda: "+adminSistema.getTienda() + "\n";
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
            AdministradorSistema_Model adminSistema = administradorSistemaRepository.findById(id).get();
            Output += "Id: "+adminSistema.getIdAdmin() + "\n";
            Output += "Nombre: "+adminSistema.getNombre() + "\n";
            Output += "Email: "+adminSistema.getEmail() + "\n";
            Output += "Password: "+adminSistema.getPassword() + "\n";
            Output += "Tienda: "+adminSistema.getTienda() + "\n";
            return Output;
        }else  {
            return "Administrador de Sistema no encontrado";
        }
    }

    public String actualizar_AdministradorSistema(int id, AdministradorSistema_Model administradorSistema){
        if(administradorSistemaRepository.existsById(id)){
            AdministradorSistema_Model administradorSistemaB = administradorSistemaRepository.findById(id).get();
            administradorSistemaB.setNombre(administradorSistema.getNombre());
            administradorSistemaB.setEmail(administradorSistema.getEmail());
            administradorSistemaB.setPassword(administradorSistema.getPassword());
            administradorSistemaB.setTienda(administradorSistema.getTienda());
            administradorSistemaRepository.save(administradorSistemaB);
            return "Administrador de Sistema actualizado con exito";
        }else  {
            return "Administrador de Sistema no encontrado";
        }
    }
}
