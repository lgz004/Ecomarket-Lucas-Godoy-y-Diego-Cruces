package com.example.demo.Service;

import com.example.demo.Model.GerenteTienda_Model;
import com.example.demo.Repository.GerenteTienda_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GerenteTienda_Service {

    @Autowired
    GerenteTienda_Repository gerenteTienda_Repository;

    public String agregar_Gerente(GerenteTienda_Model gerente){
        gerenteTienda_Repository.save(gerente);
        return "Gerente agregado con exito";
    }

    public String eliminar_Gerente(int id){
        if(gerenteTienda_Repository.existsById(id)){
            gerenteTienda_Repository.deleteById(id);
            return "Gerente eliminado con exito";
        }else {
            return "Gerente no encontrado";
        }
    }

    public String Listar_Gerentes(){
        String Output = "";
        for(GerenteTienda_Model gerente : gerenteTienda_Repository.findAll()){
            Output += "Id: "+gerente.getIdGerente() + "\n";
            Output += "Nombre: "+gerente.getNombre() + "\n";
            Output += "Email: "+gerente.getEmail() + "\n";
            Output += "Password: "+gerente.getPassword() + "\n";
            Output += "Tienda: "+gerente.getTiendaAsignada() + "\n";
        }
        if (Output.isEmpty()){
            return "No hay gerentes";
        }else  {
            return Output;
        }
    }

    public String obtener_GerenteId(int id){
        String Output = "";
        if(gerenteTienda_Repository.existsById(id)){
            GerenteTienda_Model gerente = gerenteTienda_Repository.findById(id).get();
            Output += "Id: "+gerente.getNombre() + "\n";
            Output += "Nombre: "+gerente.getNombre() + "\n";
            Output += "Email: "+gerente.getEmail() + "\n";
            Output += "Password: "+gerente.getPassword() + "\n";
            Output += "Tienda: "+gerente.getTiendaAsignada() + "\n";
            return Output;
        }else  {
            return "Gerente no encontrado";
        }
    }

    public String actualizar_Gerente(int id, GerenteTienda_Model gerente){
        if(gerenteTienda_Repository.existsById(id)){
            GerenteTienda_Model gerenteb = gerenteTienda_Repository.findById(id).get();
            gerenteb.setNombre(gerente.getNombre());
            gerenteb.setEmail(gerente.getEmail());
            gerenteb.setPassword(gerente.getPassword());
            gerenteTienda_Repository.save(gerenteb);
            return "Gerente actualizado con exito";
        }else  {
            return "Gerente no encontrado";
        }
    }
}
