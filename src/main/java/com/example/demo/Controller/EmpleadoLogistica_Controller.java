package com.example.demo.Controller;

import com.example.demo.Assembler.EmpleadoLogisticaModelAssembler;
import com.example.demo.Model.EmpleadoLogistica_Model;
import com.example.demo.Service.EmpleadoLogistica_Service;
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
@RequestMapping("/empleadosLogistica")
@Tag(name = "Controller empleadosLogistica", description = "Servicio gestion de empleados de logistica Ecomarket")
public class EmpleadoLogistica_Controller {

    @Autowired
    private EmpleadoLogistica_Service empleadoLogistica_Service;

    @Autowired
    EmpleadoLogisticaModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener Empleados de Logistica", description = "Obtiene la lista completa de empleados de logistica registrados en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna lista completa de empleados de logistica"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    public ResponseEntity<CollectionModel<EntityModel<EmpleadoLogistica_Model>>> getEmpleadosLogistica(){
        List<EmpleadoLogistica_Model> lista = empleadoLogistica_Service.Listar_EmpleadoLogistica();
        if (lista.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else  {
            return new ResponseEntity<>(assembler.toCollectionModel(lista), HttpStatus.OK);
        }
    }

    @PostMapping
    @Operation(summary = "Agregar empleado de logistica",description = "Permite registrar un empleado de logistica en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Empleado de logistica creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmpleadoLogistica_Model.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    public ResponseEntity<EntityModel<EmpleadoLogistica_Model>> addEmpleadoLogistica(@RequestBody EmpleadoLogistica_Model empleadoLogistica){
        empleadoLogistica_Service.agregar_EmpleadoLogistica(empleadoLogistica);
        if (empleadoLogistica_Service.obtener_EmpleadoLogisticaId(empleadoLogistica.getIdLogistica()).isPresent()) {
            return new ResponseEntity<>(assembler.toModel(empleadoLogistica), HttpStatus.CREATED);
        } else  {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar empleado de logistica por ID", description = "Obtiene un empleado de logistica segun el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna empleado de logistica"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del empleado de logistica", example = "123")
    public ResponseEntity<EntityModel<EmpleadoLogistica_Model>> getEmpleadoLogisticaId(@PathVariable int id){
        if (empleadoLogistica_Service.obtener_EmpleadoLogisticaId(id).isPresent()) {
            EmpleadoLogistica_Model logistica = empleadoLogistica_Service.obtener_EmpleadoLogisticaId(id).get();
            return new ResponseEntity<>(assembler.toModel(logistica), HttpStatus.OK);
        } else   {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un empleado de logistica por ID", description = "Elimina un empleado de logistica segun el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empleado de logistica eliminado"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del empleado de logistica", example = "123")
    public ResponseEntity<Void> eliminarEmpleadoLogistica(@PathVariable int id){
        if (empleadoLogistica_Service.obtener_EmpleadoLogisticaId(id).isPresent()) {
            empleadoLogistica_Service.eliminar_EmpleadoLogistica(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else   {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar empleado de logistica", description = "Permite actualizar los datos de algun empleado de logistica mediante la ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empleado de logistica modificado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmpleadoLogistica_Model.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    @Parameter(description = "El ID del empleado de logistica", example = "123")
    public ResponseEntity<EmpleadoLogistica_Model> editarEmpleadoLogistica(@PathVariable int id, @RequestBody EmpleadoLogistica_Model empleadoLogistica){
        if (empleadoLogistica_Service.obtener_EmpleadoLogisticaId(id).isPresent()) {
            empleadoLogistica_Service.actualizar_EmpleadoLogistica(id, empleadoLogistica);
            return new ResponseEntity<>(HttpStatus.OK);
        } else   {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
