package com.example.demo.Controller;

import com.example.demo.Assembler.UsuarioModelAssembler;
import com.example.demo.Model.Usuario_Model;
import com.example.demo.Service.Usuario_Service;
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
@RequestMapping("/usuarios")
@Tag(name = "Controller Usuarios", description = "Servicio gestion de usuarios Ecomarket")
public class Usuario_Controller {
    @Autowired
    private Usuario_Service usuario_service;

    @Autowired
    UsuarioModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener usuarios", description = "Obtiene la lista completa de usuarios registrados en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna lista completa de usuarios"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    public ResponseEntity<CollectionModel<EntityModel<Usuario_Model>>> getUsuarios() {
        List<Usuario_Model> lista = usuario_service.Listar_Usuarios();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else  {
            return new ResponseEntity<>(assembler.toCollectionModel(lista), HttpStatus.OK);
        }
    }

    @PostMapping
    @Operation(summary = "Agregar usuario",description = "Permite registrar un usuario en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario_Model.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    public ResponseEntity<EntityModel<Usuario_Model>> addUsuario(@RequestBody Usuario_Model usuario) {
        usuario_service.agregar_Usuario(usuario);
        if (usuario_service.obtener_Usuario(usuario.getIdUsuario()).isPresent()) {
            return new ResponseEntity<>(assembler.toModel(usuario),HttpStatus.CREATED);
        } else  {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuario por ID", description = "Obtiene un usuario segun el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna usuario"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del usuario", example = "123")
    public ResponseEntity<EntityModel<Usuario_Model>> getUsuarioById(@PathVariable int id) {
        if (usuario_service.obtener_Usuario(id).isPresent()) {
            Usuario_Model usuario = usuario_service.obtener_Usuario(id).get();
            return new ResponseEntity<>(assembler.toModel(usuario),HttpStatus.OK);
        }else  {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un usuario por ID", description = "Elimina un usuario segun el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario eliminado"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del usuario", example = "123")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable int id) {
        if (usuario_service.obtener_Usuario(id).isPresent()) {
            usuario_service.eliminar_Usuario(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else   {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar usuario", description = "Permite actualizar los datos de algun usuario mediante la ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario modificado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario_Model.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    @Parameter(description = "El ID del usuario", example = "123")
    public ResponseEntity<Usuario_Model> editarUsuario(@PathVariable int id, @RequestBody Usuario_Model usuario) {
        if (usuario_service.obtener_Usuario(id).isPresent()) {
            usuario_service.actualizar_Usuario(id, usuario);
            return new ResponseEntity<>(HttpStatus.OK);
        } else   {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
