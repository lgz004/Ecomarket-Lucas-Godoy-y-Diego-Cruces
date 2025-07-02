package com.example.demo.Assembler;

import com.example.demo.Controller.AdministradorSistema_Controller;
import com.example.demo.Model.AdministradorSistema_Model;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class AdministradorSistemaModelAssembler implements RepresentationModelAssembler<AdministradorSistema_Model, EntityModel<AdministradorSistema_Model>> {

    @Override
    public EntityModel<AdministradorSistema_Model> toModel(AdministradorSistema_Model AdministradorSistema) {
        return EntityModel.of(AdministradorSistema,
                linkTo(methodOn(AdministradorSistema_Controller.class).getAdministradorSistemaById(AdministradorSistema.getIdAdmin())).withSelfRel(),
                linkTo(methodOn(AdministradorSistema_Controller.class).getAdministradoresSistema()).withRel("AdminSistema"),
                linkTo(methodOn(AdministradorSistema_Controller.class).editarAdministradorSistema(AdministradorSistema.getIdAdmin(), AdministradorSistema)).withRel("PUT"),
                linkTo(methodOn(AdministradorSistema_Controller.class).deleteAdministradorSistemaById(AdministradorSistema.getIdAdmin())).withRel("DELETE"));
    }
}
