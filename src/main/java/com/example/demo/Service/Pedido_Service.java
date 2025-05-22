package com.example.demo.Service;

import com.example.demo.Model.Pedido_Model;
import com.example.demo.Repository.Pedido_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Pedido_Service {
    @Autowired
    Pedido_Repository pedido_Repository;

    public String agregarPedido(Pedido_Model pedido) {
        pedido_Repository.save(pedido);
        return "Pedido agregado exitosamente";
    }

    public String eliminarPedido(int id) {
        if (pedido_Repository.existsById(id)) {
            pedido_Repository.deleteById(id);
            return "Pedido eliminado exitosamente";
        }else{
            return "El pedido no existe";
        }
    }

    public String obtenerPedidoId(int id) {
        String output = "";
        if (pedido_Repository.existsById(id)) {
            Pedido_Model pedido = pedido_Repository.findById(id).get();
            output +="ID Pedido: "+pedido.getIdPedido()+"\n";
            output +="Total: "+pedido.getTotalPedido()+"\n";
            return output;
        }else{
            return "El pedido no existe";
        }
    }

    public String listarPedidos(){
        String output = "";
        for (Pedido_Model pedido : pedido_Repository.findAll()) {
            output +="ID Pedido: "+pedido.getIdPedido()+"\n";
            output +="Total: "+pedido.getTotalPedido()+"\n";
        }
        if (output.isEmpty()) {
            return "No hay pedidos";
        }else {
            return output;
        }
    }

    public String actualizarPedido(int id, Pedido_Model pedido) {
        if (pedido_Repository.existsById(id)) {
            Pedido_Model pedido_actual = pedido_Repository.findById(id).get();
            pedido_actual.setTotalPedido(pedido.getTotalPedido());
            pedido_Repository.save(pedido_actual);
            return "Pedido actualizado exitosamente";
        }else {
            return "El pedido no existe";
        }
    }
}
