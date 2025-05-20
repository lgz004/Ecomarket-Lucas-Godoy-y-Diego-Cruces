package com.example.demo.Controller;

import com.example.demo.Model.Producto_Model;
import com.example.demo.Service.Producto_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
public class Product_Controller {

    @Autowired
    private Producto_Service productoService;

    @GetMapping
    public String getProductos() {
        return productoService.listarProductos();
    }

    @PostMapping
    public String addProducto(@RequestBody Producto_Model producto) {
        return productoService.agregarProducto(producto);
    }

    @GetMapping("/{id}")
    public String getProducto(@PathVariable int id) {
        return productoService.obtenerProductoId(id);
    }

    @DeleteMapping("/{id}")
    public String deleteProducto(@PathVariable int id) {
        return productoService.eliminarProducto(id);
    }

    @PutMapping("/{id}")
    public String updateProducto(@PathVariable int id, @RequestBody Producto_Model producto) {
        return productoService.actualizarProducto(id, producto);
    }
}
