package com.example.demo.Controller;

import com.example.demo.Model.Pedido_Model;
import com.example.demo.Service.Pedido_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class Pedido_Controller {
    @Autowired
    private Pedido_Service pedido_Service;

    @GetMapping
    public String getPedidos() {
        return pedido_Service.listarPedidos();
    }

    @PostMapping
    public String addPedido(@RequestBody Pedido_Model pedido) {
        return pedido_Service.agregarPedido(pedido);
    }

    @GetMapping("/{id}")
    public String getPedido(@PathVariable int id) {
        return pedido_Service.obtenerPedidoId(id);
    }

    @DeleteMapping("/{id}")
    public String deletePedido(@PathVariable int id) {
        return pedido_Service.eliminarPedido(id);
    }

    @PutMapping("/{id}")
    public String updatePedido(@PathVariable int id, @RequestBody Pedido_Model pedido) {
        return pedido_Service.actualizarPedido(id, pedido);
    }
}
