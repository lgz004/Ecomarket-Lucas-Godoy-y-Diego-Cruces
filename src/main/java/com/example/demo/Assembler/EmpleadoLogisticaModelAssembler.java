package com.example.demo.Assembler;


import com.example.demo.Controller.EmpleadoLogistica_Controller;
import com.example.demo.Model.EmpleadoLogistica_Model;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class EmpleadoLogisticaModelAssembler implements RepresentationModelAssembler<EmpleadoLogistica_Model, EntityModel<EmpleadoLogistica_Model>> {

    @Override
    public EntityModel<EmpleadoLogistica_Model> toModel(EmpleadoLogistica_Model EmpleadoLogistica) {
        return EntityModel.of(EmpleadoLogistica,
                linkTo(methodOn(EmpleadoLogistica_Controller.class).getEmpleadoLogisticaId(EmpleadoLogistica.getIdLogistica())).withSelfRel(),
                linkTo(methodOn(EmpleadoLogistica_Controller.class).getEmpleadosLogistica()).withRel("empleadoLogistica"),
                linkTo(methodOn(EmpleadoLogistica_Controller.class).editarEmpleadoLogistica(EmpleadoLogistica.getIdLogistica(), EmpleadoLogistica)).withRel("PUT"),
                linkTo(methodOn(EmpleadoLogistica_Controller.class).eliminarEmpleadoLogistica(EmpleadoLogistica.getIdLogistica())).withRel("DELETE")
        );
    }
}
