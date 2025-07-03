package com.example.demo.Service;

import com.example.demo.Model.Cliente_Model;
import com.example.demo.Model.Pedido_Model;
import com.example.demo.Model.Usuario_Model;
import com.example.demo.Repository.Cliente_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Cliente_Service {

    @Autowired
    Cliente_Repository cliente_Repository;

    public void agregarCliente(Cliente_Model cliente) {
        cliente_Repository.save(cliente);
    }

    public void eliminarCliente(int id) {
        cliente_Repository.deleteById(id);
    }

    public List<Cliente_Model> listarClientes(){
        return cliente_Repository.findAll();
    }

    public Optional<Cliente_Model> obtenerCliente(int id){
        return cliente_Repository.findById(id);
    }

    public void actualizarCliente(int id, Cliente_Model cliente){
        Cliente_Model cliente_actual = cliente_Repository.findById(id).get();
        cliente_actual.setNombre(cliente.getNombre());
        cliente_actual.setEmail(cliente.getEmail());
        cliente_actual.setPassword(cliente.getPassword());
        cliente_actual.setDireccionEnvio(cliente.getDireccionEnvio());
        cliente_Repository.save(cliente_actual);
    }
}
