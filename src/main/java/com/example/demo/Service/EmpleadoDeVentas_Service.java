package com.example.demo.Service;

import com.example.demo.Model.EmpleadoDeVentas_Model;
import com.example.demo.Repository.EmpleadoDeVentas_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EmpleadoDeVentas_Service {
    @Autowired
    EmpleadoDeVentas_Repository empleadoDeVentas_Repository;

    public void agregar_EmpleadoDeVentas(EmpleadoDeVentas_Model empleadoVentas){
        empleadoDeVentas_Repository.save(empleadoVentas);
    }

    public void eliminar_EmpleadoDeVentas(int id){
        empleadoDeVentas_Repository.deleteById(id);
    }

    public List<EmpleadoDeVentas_Model> Listar_EmpleadoDeVentas(){
        return empleadoDeVentas_Repository.findAll();
    }

    public Optional<EmpleadoDeVentas_Model> obtener_EmpleadoDeVentasId(int id){
        return empleadoDeVentas_Repository.findById(id);
    }

    public void actualizar_EmpleadoDeVentas(int id, EmpleadoDeVentas_Model EmpleadoDeVentas){
        EmpleadoDeVentas_Model empleadoDeVentas_actual = empleadoDeVentas_Repository.findById(id).get();
        empleadoDeVentas_actual.setNombre(EmpleadoDeVentas.getNombre());
        empleadoDeVentas_actual.setEmail(EmpleadoDeVentas.getEmail());
        empleadoDeVentas_actual.setPassword(EmpleadoDeVentas.getPassword());
        empleadoDeVentas_actual.setTienda(EmpleadoDeVentas.getTienda());
        empleadoDeVentas_Repository.save(empleadoDeVentas_actual);
    }
}
