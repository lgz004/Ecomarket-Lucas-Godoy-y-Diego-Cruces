package com.example.demo.Assembler;

import com.example.demo.Controller.EmpleadoDeVentas_Controller;
import com.example.demo.Model.EmpleadoDeVentas_Model;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class EmpleadoDeVentasModelAssembler implements RepresentationModelAssembler<EmpleadoDeVentas_Model, EntityModel<EmpleadoDeVentas_Model>> {

    @Override
    public EntityModel<EmpleadoDeVentas_Model> toModel(EmpleadoDeVentas_Model EmpleadoVentas) {
        return EntityModel.of(EmpleadoVentas,
                linkTo(methodOn(EmpleadoDeVentas_Controller.class).getEmpleadoDeVentasById(EmpleadoVentas.getIdEmpleado())).withSelfRel(),
                linkTo(methodOn(EmpleadoDeVentas_Controller.class).getEmpleadosDeVentas()).withRel("empleadosDeVentas"),
                linkTo(methodOn(EmpleadoDeVentas_Controller.class).editarEmpleadoDeVentas(EmpleadoVentas.getIdEmpleado(), EmpleadoVentas)).withRel("PUT"),
                linkTo(methodOn(EmpleadoDeVentas_Controller.class).eliminarEmpleadoDeVentas(EmpleadoVentas.getIdEmpleado())).withRel("DELETE"));
    }
}
