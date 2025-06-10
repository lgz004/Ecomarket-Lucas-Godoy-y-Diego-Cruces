package com.example.demo.Assembler;

import com.example.demo.Controller.Usuario_Controller;
import com.example.demo.Model.Usuario_Model;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
// UserModelAssembler.java
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario_Model, EntityModel<Usuario_Model>> {


    @Override
    public EntityModel<Usuario_Model> toModel(Usuario_Model Usuario) {
        return EntityModel.of(Usuario,
                linkTo(methodOn(Usuario_Controller.class).getUsuarioById(Usuario.getIdUsuario())).withSelfRel(),
                linkTo(methodOn(Usuario_Controller.class).getUsuarios()).withRel("Usuario")

        );
    }
}
