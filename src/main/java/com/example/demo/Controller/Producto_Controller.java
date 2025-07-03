package com.example.demo.Controller;

import com.example.demo.Assembler.PedidoModelAssembler;
import com.example.demo.Assembler.ProductoModelAssembler;
import com.example.demo.Model.Pedido_Model;
import com.example.demo.Model.Producto_Model;
import com.example.demo.Service.Producto_Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class Producto_Controller {

    @Autowired
    private Producto_Service productoService;

    @Autowired
    ProductoModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener productos", description = "Obtiene la lista completa de productos registrados en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna lista completa de productos"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    public ResponseEntity<CollectionModel<EntityModel<Producto_Model>>> getProductos() {
        List<Producto_Model> lista = productoService.listarProductos();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else  {
            return new ResponseEntity<>(assembler.toCollectionModel(lista), HttpStatus.OK);
        }
    }

    @PostMapping
    @Operation(summary = "Agregar producto",description = "Permite registrar producto en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Producto_Model.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    public ResponseEntity<EntityModel<Producto_Model>> addProducto(@RequestBody Producto_Model producto) {
        productoService.agregarProducto(producto);
        if (productoService.obtenerProducto(producto.getIdProducto()).isPresent()) {
            return new ResponseEntity<>(assembler.toModel(producto),HttpStatus.CREATED);
        } else  {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar producto por ID", description = "Obtiene un producto segun el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna producto"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del producto", example = "123")
    public ResponseEntity<EntityModel<Producto_Model>> getProducto(@PathVariable int id) {
        if (productoService.obtenerProducto(id).isPresent()) {
            Producto_Model producto = productoService.obtenerProducto(id).get();
            return new ResponseEntity<>(assembler.toModel(producto),HttpStatus.OK);
        }else  {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un producto por ID", description = "Elimina un producto segun el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto eliminado"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del producto", example = "123")
    public ResponseEntity<Void> deleteProducto(@PathVariable int id) {
        if (productoService.obtenerProducto(id).isPresent()) {
            productoService.eliminarProducto(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else   {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar producto", description = "Permite actualizar los datos de algun producto mediante la ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto modificado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Producto_Model.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    public ResponseEntity<Producto_Model> updateProducto(@PathVariable int id, @RequestBody Producto_Model producto) {
        if (productoService.obtenerProducto(id).isPresent()) {
            productoService.actualizarProducto(id, producto);
            return new ResponseEntity<>(HttpStatus.OK);
        } else   {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
