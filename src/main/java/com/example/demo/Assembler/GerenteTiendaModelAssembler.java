package com.example.demo.Assembler;

import com.example.demo.Controller.GerenteTienda_Controller;
import com.example.demo.Model.GerenteTienda_Model;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class GerenteTiendaModelAssembler implements RepresentationModelAssembler<GerenteTienda_Model, EntityModel<GerenteTienda_Model>> {

    @Override
    public EntityModel<GerenteTienda_Model> toModel(GerenteTienda_Model GerenteTienda) {
        return EntityModel.of(GerenteTienda,
                linkTo(methodOn(GerenteTienda_Controller.class).getGerenteById(GerenteTienda.getIdGerente())).withSelfRel(),
                linkTo(methodOn(GerenteTienda_Controller.class).getGerentes()).withRel("gerentes"),
                linkTo(methodOn(GerenteTienda_Controller.class).editGerente(GerenteTienda.getIdGerente(), GerenteTienda)).withRel("PUT"),
                linkTo(methodOn(GerenteTienda_Controller.class).deleteGerente(GerenteTienda.getIdGerente())).withRel("DELETE")
        );
    }
}
