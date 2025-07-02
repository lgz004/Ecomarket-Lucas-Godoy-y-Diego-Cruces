package com.example.demo.Assembler;

import com.example.demo.Controller.Pedido_Controller;
import com.example.demo.Model.Pedido_Model;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PedidoModelAssembler implements RepresentationModelAssembler<Pedido_Model, EntityModel<Pedido_Model>> {

    @Override
    public EntityModel<Pedido_Model> toModel(Pedido_Model Pedido) {
        return EntityModel.of(Pedido,
                linkTo(methodOn(Pedido_Controller.class).getPedido(Pedido.getIdPedido())).withSelfRel(),
                linkTo(methodOn(Pedido_Controller.class).getPedidos()).withRel("Pedido"),
                linkTo(methodOn(Pedido_Controller.class).updatePedido(Pedido.getIdPedido(), Pedido)).withRel("PUT"),
                linkTo(methodOn(Pedido_Controller.class).deletePedido(Pedido.getIdPedido())).withRel("DELETE")
        );
    }
}
