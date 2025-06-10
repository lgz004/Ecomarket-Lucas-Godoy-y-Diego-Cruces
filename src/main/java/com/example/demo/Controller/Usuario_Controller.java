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
    public String getUsuarios() {
        return usuario_service.Listar_Usuarios();
    }

    @PostMapping
    @Operation(summary = "Agregar usuario",description = "Permite registrar un usuario en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario_Model.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    public String addUsuario(@RequestBody Usuario_Model usuario) {
        return usuario_service.agregar_Usuario(usuario);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuario por ID", description = "Obtiene un usuario segu el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna usuario"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del usuario", example = "123")
    public String getUsuarioById(@PathVariable int id) {
        return usuario_service.obtener_Usuario(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un usuario por ID", description = "Elimina un usuario segun el ID registrado en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario eliminado"),
            @ApiResponse(responseCode = "404", description = "No se encuentran datos")
    })
    @Parameter(description = "El ID del usuario", example = "123")
    public String eliminarUsuario(@PathVariable int id) {
        return usuario_service.eliminar_Usuario(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar usuario", description = "Permite actualizar los datos de algun usuario mediante la ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario modificado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario_Model.class))),
            @ApiResponse(responseCode = "204", description = "No hay contenido en la solicitud")
    })
    public String editarUsuario(@PathVariable int id, @RequestBody Usuario_Model usuario) {
        return usuario_service.actualizar_Usuario(id, usuario);
    }
}
