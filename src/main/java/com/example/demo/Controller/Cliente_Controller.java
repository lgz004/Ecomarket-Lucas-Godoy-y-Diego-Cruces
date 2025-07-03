package com.example.demo.Controller;

import com.example.demo.Assembler.ClienteModelAssembler;
import com.example.demo.Model.Cliente_Model;
import com.example.demo.Model.Pedido_Model;
import com.example.demo.Service.Cliente_Service;
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
@RequestMapping("/clientes")
@Tag(name = "Controller clientes", description = "Servicio gestion de clientes Ecomarket")
public class Cliente_Controller {

    @Autowired
    Cliente_Service cliente_service;

    @Autowired
    ClienteModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener clientes", description = "Obtiene la lista completa de clientes registrados en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna lista completa de clientes"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    public ResponseEntity<CollectionModel<EntityModel<Cliente_Model>>> getClientes() {
        List<Cliente_Model> lista = cliente_service.listarClientes();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else  {
            return new ResponseEntity<>(assembler.toCollectionModel(lista), HttpStatus.OK);
        }
    }

    @PostMapping
    @Operation(summary = "Agregar cliente",description = "Permite registrar cliente en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente_Model.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    public ResponseEntity<EntityModel<Cliente_Model>> addCliente(@RequestBody Cliente_Model cliente) {
        cliente_service.agregarCliente(cliente);
        if (cliente_service.obtenerCliente(cliente.getIdCliente()).isPresent()) {
            return new ResponseEntity<>(assembler.toModel(cliente),HttpStatus.CREATED);
        } else  {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar cliente por ID", description = "Obtiene un cliente segun el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna cliente"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del cliente", example = "123")
    public ResponseEntity<EntityModel<Cliente_Model>> getCliente(@PathVariable int id) {
        if (cliente_service.obtenerCliente(id).isPresent()) {
            Cliente_Model cliente = cliente_service.obtenerCliente(id).get();
            return new ResponseEntity<>(assembler.toModel(cliente),HttpStatus.OK);
        }else  {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un cliente por ID", description = "Elimina un cliente segun el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente eliminado"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del cliente", example = "123")
    public ResponseEntity<Void> deleteCliente(@PathVariable int id) {
        if (cliente_service.obtenerCliente(id).isPresent()) {
            cliente_service.eliminarCliente(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else   {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar cliente", description = "Permite actualizar los datos de algun cliente mediante la ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente modificado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente_Model.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    public ResponseEntity<Cliente_Model> updateCliente(@PathVariable int id, @RequestBody Cliente_Model cliente) {
        if (cliente_service.obtenerCliente(id).isPresent()) {
            cliente_service.actualizarCliente(id, cliente);
            return new ResponseEntity<>(HttpStatus.OK);
        } else   {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
