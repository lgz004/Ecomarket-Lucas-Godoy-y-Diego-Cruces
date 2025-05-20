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

    public String eliminarProducto(int id) {
        if (producto_Repository.existsById(id)) {
            producto_Repository.deleteById(id);
            return "Producto eliminado exitosamente";
        }else{
            return "El producto no existe";
        }
    }

    public String obtenerProductoId(int id) {
        String output = "";
        if (producto_Repository.existsById(id)) {
            Producto_Model producto = producto_Repository.findById(id).get();
            output +="ID Producto: "+producto.getIdProducto()+"\n";
            output +="Nombre: "+producto.getNombre()+"\n";
            output +="Precio: $"+producto.getPrecio()+"\n";
            output +="Stock: "+producto.getStock()+"\n";
            return output;
        }else{
            return "El producto no existe";
        }
    }

    public String listarProductos(){
        String output = "";
        for (Producto_Model producto : producto_Repository.findAll()) {
            output +="ID Producto: "+producto.getIdProducto()+"\n";
            output +="Nombre: "+producto.getNombre()+"\n";
            output +="Precio: $"+producto.getPrecio()+"\n";
            output +="Stock: "+producto.getStock()+"\n";
        }
        if (output.isEmpty()) {
            return "No hay productos";
        }else {
            return output;
        }
    }

    public String actualizarProducto(int id, Producto_Model producto) {
        if (producto_Repository.existsById(id)) {
            Producto_Model producto_actual = producto_Repository.findById(id).get();
            producto_actual.setNombre(producto.getNombre());
            producto_actual.setPrecio(producto.getPrecio());
            producto_actual.setStock(producto.getStock());
            producto_Repository.save(producto_actual);
            return "Producto actualizado exitosamente";
        }else {
            return "El producto no existe";
        }
    }
}
