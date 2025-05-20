package com.example.demo.Service;

import com.example.demo.Model.Producto_Model;
import com.example.demo.Repository.Producto_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Producto_Service {
    @Autowired
    Producto_Repository producto_Repository;

    public String agregarProducto(Producto_Model producto) {
        producto_Repository.save(producto);
        return "Producto agregado exitosamente";
    }
}
