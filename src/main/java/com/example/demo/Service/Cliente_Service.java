package com.example.demo.Service;

import com.example.demo.Model.Cliente_Model;
import com.example.demo.Model.Usuario_Model;
import com.example.demo.Repository.Cliente_Repository;
import com.example.demo.Repository.Usuario_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Cliente_Service {

    @Autowired
    Cliente_Repository cliente_Repository;

    public String agregar_Cliente(Cliente_Model cliente) {
        cliente_Repository.save(cliente);
        return "Cliente agregado con exito";
    }

    public String eliminar_Cliente(int id){
        if(cliente_Repository.existsById(id)){
            cliente_Repository.deleteById(id);
            return "Cliente eliminado con exito";
        }else {
            return "Cliente no encontrado";
        }
    }

    public String Listar_Clientes(){
        String Output = "";
        for(Cliente_Model cliente : cliente_Repository.findAll()){
            Output += "Id: "+cliente.getIdUsuario() + "\n";
            Output += "Nombre: "+cliente.getNombre() + "\n";
            Output += "Email: "+cliente.getEmail() + "\n";
            Output += "Password: "+cliente.getPassword() + "\n";
            Output += "Dirección: "+cliente.getDireccionEnvio() + "\n";
        }
        if (Output.isEmpty()){
            return "No hay Clientes";
        }else  {
            return Output;
        }
    }

    public String obtener_ClienteId(int id){
        String Output = "";
        if(cliente_Repository.existsById(id)){
            Cliente_Model cliente = cliente_Repository.findById(id).get();
            Output += "Id: "+cliente.getIdUsuario() + "\n";
            Output += "Nombre: "+cliente.getNombre() + "\n";
            Output += "Email: "+cliente.getEmail() + "\n";
            Output += "Password: "+cliente.getPassword() + "\n";
            Output += "Direccion: "+cliente.getDireccionEnvio() + "\n";
            return Output;
        }else  {
            return "Cliente no encontrado";
        }
    }

    public String actualizar_Cliente(int id, Cliente_Model cliente){
        if(cliente_Repository.existsById(id)){
            Cliente_Model clienteb = cliente_Repository.findById(id).get();
            cliente.setNombre(clienteb.getNombre());
            cliente.setEmail(clienteb.getEmail());
            cliente.setPassword(clienteb.getPassword());
            cliente.setDireccionEnvio(clienteb.getDireccionEnvio());
            cliente_Repository.save(clienteb);
            return "Cliente actualizado con exito";
        }else  {
            return "Cliente no encontrado";
        }
    }
}
