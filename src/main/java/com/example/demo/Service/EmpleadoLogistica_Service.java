package com.example.demo.Service;

import com.example.demo.Model.EmpleadoLogistica_Model;
import com.example.demo.Repository.EmpleadoLogistica_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoLogistica_Service {

    @Autowired
    EmpleadoLogistica_Repository empleadoLogisticaRepository;

    public void agregar_EmpleadoLogistica(EmpleadoLogistica_Model empleadoLogistica){
        empleadoLogisticaRepository.save(empleadoLogistica);
    }

    public void eliminar_EmpleadoLogistica(int id){
        empleadoLogisticaRepository.deleteById(id);
    }

    public List<EmpleadoLogistica_Model> Listar_EmpleadoLogistica(){
        return empleadoLogisticaRepository.findAll();
    }

    public Optional<EmpleadoLogistica_Model> obtener_EmpleadoLogisticaId(int id){
        return empleadoLogisticaRepository.findById(id);
    }

    public void actualizar_EmpleadoLogistica(int id, EmpleadoLogistica_Model empleadoLogistica) {
        EmpleadoLogistica_Model empleadoLogistica_actual = empleadoLogisticaRepository.findById(id).get();
        empleadoLogistica_actual.setNombre(empleadoLogistica.getNombre());
        empleadoLogistica_actual.setEmail(empleadoLogistica.getEmail());
        empleadoLogistica_actual.setPassword(empleadoLogistica.getPassword());
        empleadoLogistica_actual.setTiendaAsignada(empleadoLogistica.getTiendaAsignada());
        empleadoLogisticaRepository.save(empleadoLogistica_actual);
    }
}
