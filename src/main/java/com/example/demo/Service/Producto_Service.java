package com.example.demo.Service;

import com.example.demo.Model.Producto_Model;
import com.example.demo.Model.Usuario_Model;
import com.example.demo.Repository.Producto_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Producto_Service {
    @Autowired
    Producto_Repository producto_Repository;

    public void agregarProducto(Producto_Model producto){
        producto_Repository.save(producto);
    }

    public void eliminarProducto(int id){
        producto_Repository.deleteById(id);
    }

    public Optional<Producto_Model> obtenerProducto(int id){
        return producto_Repository.findById(id);
    }

    public List<Producto_Model> listarProductos(){
        return producto_Repository.findAll();
    }

    public void actualizarProducto(int id, Producto_Model producto){
        Producto_Model producto_actual = producto_Repository.findById(id).get();
        producto_actual.setNombre(producto.getNombre());
        producto_actual.setPrecio(producto.getPrecio());
        producto_actual.setStock(producto.getStock());
        producto_Repository.save(producto_actual);
    }
}
