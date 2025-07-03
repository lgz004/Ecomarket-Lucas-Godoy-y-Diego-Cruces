package com.example.demo.Service;

import com.example.demo.Model.Pedido_Model;
import com.example.demo.Repository.Pedido_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Pedido_Service {
    @Autowired
    Pedido_Repository pedido_Repository;

    public void agregarPedido(Pedido_Model pedido) {
        pedido_Repository.save(pedido);
    }

    public void eliminarPedido(int id) {
        pedido_Repository.deleteById(id);
    }

    public Optional<Pedido_Model> obtenerPedido(int id){
        return pedido_Repository.findById(id);
    }

    public List<Pedido_Model> listarPedidos(){
        return pedido_Repository.findAll();
    }

    public void actualizarPedido(int id, Pedido_Model pedido){
        Pedido_Model pedido_actual = pedido_Repository.findById(id).get();
        pedido_actual.setTotalPedido(pedido.getTotalPedido());
        pedido_Repository.save(pedido_actual);
    }
}
