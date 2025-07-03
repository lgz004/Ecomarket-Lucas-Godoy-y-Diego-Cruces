package com.example.demo.Controller;

import com.example.demo.Assembler.AdministradorSistemaModelAssembler;
import com.example.demo.Model.AdministradorSistema_Model;
import com.example.demo.Service.AdministradorSistema_Service;
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
@RequestMapping("/AdminsSistema")
@Tag(name = "Controller Administradores de sistema", description = "Servicio gestion de Administradores de sistema Ecomarket")
public class AdministradorSistema_Controller {
    @Autowired
    private AdministradorSistema_Service administradorSistemaService;

    @Autowired
    AdministradorSistemaModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener Administradores de sistema", description = "Obtiene la lista completa de Administradores de sistema registrados en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna lista completa de Administradores de sistema"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    public ResponseEntity<CollectionModel<EntityModel<AdministradorSistema_Model>>> getAdministradoresSistema(){
        List<AdministradorSistema_Model> lista = administradorSistemaService.Listar_AdministradorSistema();
        if(lista.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(assembler.toCollectionModel(lista),HttpStatus.OK);
        }
    }

    @PostMapping
    @Operation(summary = "Agregar Administrador de sistema",description = "Permite registrar un Administrador de sistema en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Administrador de sistema creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdministradorSistema_Model.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    public ResponseEntity<EntityModel<AdministradorSistema_Model>> addAdministradorSistema(@RequestBody AdministradorSistema_Model administradorSistema){
        administradorSistemaService.agregar_AdministradorSistema(administradorSistema);
        if (administradorSistemaService.obtener_AdministradorSistemaId(administradorSistema.getIdAdmin()).isPresent()) {
            return new ResponseEntity<>(assembler.toModel(administradorSistema), HttpStatus.CREATED);
        } else  {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Administrador de sistema por ID", description = "Obtiene un Administrador de sistema segun el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna Administrador de sistema"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del Administrador de sistema", example = "123")
    public ResponseEntity<EntityModel<AdministradorSistema_Model>> getAdministradorSistemaById(@PathVariable int id){
        if (administradorSistemaService.obtener_AdministradorSistemaId(id).isPresent()) {
            AdministradorSistema_Model adminSistema = administradorSistemaService.obtener_AdministradorSistemaId(id).get();
            return new ResponseEntity<>(assembler.toModel(adminSistema), HttpStatus.OK);
        } else  {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un Administrador de sistema por ID", description = "Elimina un Administrador de sistema segun el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrador de sistema eliminado"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del Administrador de sistema", example = "123")
    public ResponseEntity<Void> deleteAdministradorSistemaById(@PathVariable int id){
        if (administradorSistemaService.obtener_AdministradorSistemaId(id).isPresent()) {
            administradorSistemaService.eliminar_AdministradorSistema(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else   {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar Administrador de sistema", description = "Permite actualizar los datos de algun Administrador de sistema mediante la ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrador de sistema modificado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdministradorSistema_Model.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    @Parameter(description = "El ID del Administrador de sistema", example = "123")
    public ResponseEntity<AdministradorSistema_Model> editarAdministradorSistema(@PathVariable int id, @RequestBody AdministradorSistema_Model administradorSistema){
        if (administradorSistemaService.obtener_AdministradorSistemaId(id).isPresent()) {
            administradorSistemaService.actualizar_AdministradorSistema(id, administradorSistema);
            return new ResponseEntity<>(HttpStatus.OK);
        } else   {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
