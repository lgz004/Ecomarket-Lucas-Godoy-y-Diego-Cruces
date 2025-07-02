package com.example.demo.Assembler;

import com.example.demo.Controller.Producto_Controller;
import com.example.demo.Model.Producto_Model;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProductoModelAssembler implements RepresentationModelAssembler<Producto_Model, EntityModel<Producto_Model>> {

    @Override
    public EntityModel<Producto_Model> toModel(Producto_Model Producto) {
        return EntityModel.of(Producto,
                linkTo(methodOn(Producto_Controller.class).getProducto(Producto.getIdProducto())).withSelfRel(),
                linkTo(methodOn(Producto_Controller.class).getProductos()).withRel("Producto"),
                linkTo(methodOn(Producto_Controller.class).updateProducto(Producto.getIdProducto(), Producto)).withRel("PUT"),
                linkTo(methodOn(Producto_Controller.class).deleteProducto(Producto.getIdProducto())).withRel("DELETE")
        );

    }
}
