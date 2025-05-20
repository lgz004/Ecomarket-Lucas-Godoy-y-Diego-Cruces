package com.example.demo.Controller;

import com.example.demo.Model.Cliente_Model;
import com.example.demo.Service.Cliente_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class Cliente_Controller {

    @Autowired
    Cliente_Service cliente_service;

    @GetMapping
    public String getClientes(){
        return cliente_service.Listar_Clientes();
    }

    @PostMapping
    public String addCliente(@RequestBody Cliente_Model cliente){
        return cliente_service.agregar_Cliente(cliente);
    }

    @GetMapping("/{id}")
    public String getClienteId(@PathVariable int id){
        return cliente_service.obtener_ClienteId(id);
    }

    @DeleteMapping("/{id}")
    public String eliminarCliente(@PathVariable int id){
        return cliente_service.eliminar_Cliente(id);
    }

    @PutMapping("/{id}")
    public String editarCliente(@PathVariable int id, @RequestBody Cliente_Model cliente){
        return cliente_service.actualizar_Cliente(id, cliente);
    }
}
