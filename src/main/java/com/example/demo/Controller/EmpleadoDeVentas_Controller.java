package com.example.demo.Controller;

import com.example.demo.Assembler.EmpleadoDeVentasModelAssembler;
import com.example.demo.Model.EmpleadoDeVentas_Model;
import com.example.demo.Service.EmpleadoDeVentas_Service;
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
@RequestMapping("/EmpleadosDeVentas")
@Tag(name = "Controller Empleados de ventas", description = "Servicio gestion de empleados de ventas Ecomarket")
public class EmpleadoDeVentas_Controller {
    @Autowired
    private EmpleadoDeVentas_Service empleadoDeVentasService;

    @Autowired
    EmpleadoDeVentasModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener empleados de ventas", description = "Obtiene la lista completa de empleados de ventas registrados en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna lista completa de empleados de ventas"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    public ResponseEntity<CollectionModel<EntityModel<EmpleadoDeVentas_Model>>> getEmpleadosDeVentas() {
        List<EmpleadoDeVentas_Model> lista = empleadoDeVentasService.Listar_EmpleadoDeVentas();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(assembler.toCollectionModel(lista), HttpStatus.OK);
        }
    }

    @PostMapping
    @Operation(summary = "Agregar empleado de ventas",description = "Permite registrar un empleado de ventas en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Empleado de ventas creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmpleadoDeVentas_Model.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    public ResponseEntity<EntityModel<EmpleadoDeVentas_Model>> addEmpleadoDeVentas(@RequestBody EmpleadoDeVentas_Model EmpleadoDeVentas) {
        empleadoDeVentasService.agregar_EmpleadoDeVentas(EmpleadoDeVentas);
        if (empleadoDeVentasService.obtener_EmpleadoDeVentasId(EmpleadoDeVentas.getIdEmpleado()).isPresent()) {
            return new ResponseEntity<>(assembler.toModel(EmpleadoDeVentas), HttpStatus.CREATED);
        } else  {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar empleado de ventas por ID", description = "Obtiene un empleado de ventas segun el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna empleado de ventas"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del empleado de ventas", example = "123")
    public ResponseEntity<EntityModel<EmpleadoDeVentas_Model>> getEmpleadoDeVentasById(@PathVariable int id) {
        if (empleadoDeVentasService.obtener_EmpleadoDeVentasId(id).isPresent()) {
            EmpleadoDeVentas_Model empVentas =  empleadoDeVentasService.obtener_EmpleadoDeVentasId(id).get();
            return new ResponseEntity<>(assembler.toModel(empVentas), HttpStatus.OK);
        } else  {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un empleado de ventas por ID", description = "Elimina un empleado de ventas segun el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empleado de ventas eliminado"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del empleado de ventas", example = "123")
    public ResponseEntity<Void> eliminarEmpleadoDeVentas(@PathVariable int id) {
        if (empleadoDeVentasService.obtener_EmpleadoDeVentasId(id).isPresent()) {
            empleadoDeVentasService.eliminar_EmpleadoDeVentas(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else   {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar empleado de ventas", description = "Permite actualizar los datos de algun empleado de ventas mediante la ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empleado de ventas modificado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmpleadoDeVentas_Model.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    @Parameter(description = "El ID del empleado de ventas", example = "123")
    public ResponseEntity<EmpleadoDeVentas_Model> editarEmpleadoDeVentas(@PathVariable int id, @RequestBody EmpleadoDeVentas_Model EmpleadoDeVentas) {
        if (empleadoDeVentasService.obtener_EmpleadoDeVentasId(id).isPresent()) {
            empleadoDeVentasService.actualizar_EmpleadoDeVentas(id, EmpleadoDeVentas);
            return new ResponseEntity<>(HttpStatus.OK);
        } else   {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
