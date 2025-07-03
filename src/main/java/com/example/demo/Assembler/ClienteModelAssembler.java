package com.example.demo.Assembler;

import com.example.demo.Controller.Cliente_Controller;
import com.example.demo.Model.Cliente_Model;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ClienteModelAssembler implements RepresentationModelAssembler<Cliente_Model, EntityModel<Cliente_Model>> {

    @Override
    public EntityModel<Cliente_Model> toModel(Cliente_Model Cliente) {
        return EntityModel.of(Cliente,
                linkTo(methodOn(Cliente_Controller.class).getCliente(Cliente.getIdCliente())).withSelfRel(),
                linkTo(methodOn(Cliente_Controller.class).getClientes()).withRel("Cliente"),
                linkTo(methodOn(Cliente_Controller.class).updateCliente(Cliente.getIdCliente(), Cliente)).withRel("PUT"),
                linkTo(methodOn(Cliente_Controller.class).deleteCliente(Cliente.getIdCliente())).withRel("DELETE"));
    }
}
