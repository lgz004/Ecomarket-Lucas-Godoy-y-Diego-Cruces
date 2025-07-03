package com.example.demo.Controller;

import com.example.demo.Assembler.GerenteTiendaModelAssembler;
import com.example.demo.Model.GerenteTienda_Model;
import com.example.demo.Model.Pedido_Model;
import com.example.demo.Service.GerenteTienda_Service;
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
@RequestMapping("/gerentes")
@Tag(name = "Controller GerenteTienda", description = "Servicio gestion de gerentes de tiendas Ecomarket")
public class GerenteTienda_Controller {

    @Autowired
    private GerenteTienda_Service gerenteTienda_Service;

    @Autowired
    GerenteTiendaModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener gerentes", description = "Obtiene la lista completa de gerentes registrados en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna lista completa de gerentes"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    public ResponseEntity<CollectionModel<EntityModel<GerenteTienda_Model>>> getGerentes() {
        List<GerenteTienda_Model> lista = gerenteTienda_Service.listarGerentes();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else  {
            return new ResponseEntity<>(assembler.toCollectionModel(lista), HttpStatus.OK);
        }
    }

    @PostMapping
    @Operation(summary = "Agregar gerente",description = "Permite registrar gerente en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Gerente creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GerenteTienda_Model.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    public ResponseEntity<EntityModel<GerenteTienda_Model>> addGerente(@RequestBody GerenteTienda_Model gerente) {
        gerenteTienda_Service.agregarGerente(gerente);
        if (gerenteTienda_Service.obtenerGerente(gerente.getIdGerente()).isPresent()) {
            return new ResponseEntity<>(assembler.toModel(gerente),HttpStatus.CREATED);
        } else  {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar gerente por ID", description = "Obtiene un gerente segun el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna gerente"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del gerente", example = "123")
    public ResponseEntity<EntityModel<GerenteTienda_Model>> getGerenteById(@PathVariable int id) {
        if (gerenteTienda_Service.obtenerGerente(id).isPresent()) {
            GerenteTienda_Model gerente = gerenteTienda_Service.obtenerGerente(id).get();
            return new ResponseEntity<>(assembler.toModel(gerente),HttpStatus.OK);
        }else  {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un gerente por ID", description = "Elimina un gerente segun el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gerente eliminado"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del gerente", example = "123")
    public ResponseEntity<Void> deleteGerente(@PathVariable int id) {
        if (gerenteTienda_Service.obtenerGerente(id).isPresent()) {
            gerenteTienda_Service.eliminarGerente(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else   {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar gerente", description = "Permite actualizar los datos de algun gerente mediante la ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Gerente modificado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GerenteTienda_Model.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    public ResponseEntity<GerenteTienda_Model> updateGerente(@PathVariable int id, @RequestBody GerenteTienda_Model gerente) {
        if (gerenteTienda_Service.obtenerGerente(id).isPresent()) {
            gerenteTienda_Service.actualizarGerente(id, gerente);
            return new ResponseEntity<>(HttpStatus.OK);
        } else   {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
