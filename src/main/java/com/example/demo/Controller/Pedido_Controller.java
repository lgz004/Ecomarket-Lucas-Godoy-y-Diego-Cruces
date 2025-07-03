package com.example.demo.Controller;

import com.example.demo.Assembler.PedidoModelAssembler;
import com.example.demo.Assembler.UsuarioModelAssembler;
import com.example.demo.Model.Pedido_Model;
import com.example.demo.Model.Usuario_Model;
import com.example.demo.Service.Pedido_Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@Tag(name = "Controller Pedidos", description = "Servicio gestion de pedidos Ecomarket")
public class Pedido_Controller {
    @Autowired
    private Pedido_Service pedido_Service;

    @Autowired
    PedidoModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener pedidos", description = "Obtiene la lista completa de pedidos registrados en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna lista completa de pedidos"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    public ResponseEntity<CollectionModel<EntityModel<Pedido_Model>>> getPedidos() {
        List<Pedido_Model> lista = pedido_Service.listarPedidos();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else  {
            return new ResponseEntity<>(assembler.toCollectionModel(lista), HttpStatus.OK);
        }
    }

    @PostMapping
    @Operation(summary = "Agregar pedido",description = "Permite registrar pedido en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pedido_Model.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    public ResponseEntity<EntityModel<Pedido_Model>> addPedido(@RequestBody Pedido_Model pedido) {
        pedido_Service.agregarPedido(pedido);
        if (pedido_Service.obtenerPedido(pedido.getIdPedido()).isPresent()) {
            return new ResponseEntity<>(assembler.toModel(pedido),HttpStatus.CREATED);
        } else  {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pedido por ID", description = "Obtiene un pedido segun el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna pedido"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del pedido", example = "123")
    public ResponseEntity<EntityModel<Pedido_Model>> getPedido(@PathVariable int id) {
        if (pedido_Service.obtenerPedido(id).isPresent()) {
            Pedido_Model pedido = pedido_Service.obtenerPedido(id).get();
            return new ResponseEntity<>(assembler.toModel(pedido),HttpStatus.OK);
        }else  {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un pedido por ID", description = "Elimina un pedido segun el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido eliminado"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del pedido", example = "123")
    public ResponseEntity<Void> deletePedido(@PathVariable int id) {
        if (pedido_Service.obtenerPedido(id).isPresent()) {
            pedido_Service.eliminarPedido(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else   {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar pedido", description = "Permite actualizar los datos de algun pedido mediante la ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido modificado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pedido_Model.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    public ResponseEntity<Pedido_Model> updatePedido(@PathVariable int id, @RequestBody Pedido_Model pedido) {
        if (pedido_Service.obtenerPedido(id).isPresent()) {
            pedido_Service.actualizarPedido(id, pedido);
            return new ResponseEntity<>(HttpStatus.OK);
        } else   {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
