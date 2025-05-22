package com.example.demo.Service;

import com.example.demo.Model.EmpleadoLogistica_Model;
import com.example.demo.Repository.EmpleadoLogistica_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoLogistica_Service {

    @Autowired
    EmpleadoLogistica_Repository empleadoLogisticaRepository;

    public String agregar_EmpleadoLogistica(EmpleadoLogistica_Model empleadoLogistica){
        empleadoLogisticaRepository.save(empleadoLogistica);
        return "Empleado de Logistica agregado con exito";
    }

    public String eliminar_EmpleadoLogistica(int id){
        if(empleadoLogisticaRepository.existsById(id)){
            empleadoLogisticaRepository.deleteById(id);
            return "Empleado de Logistica eliminado con exito";
        }else {
            return "Empleado de Logistica no encontrado";
        }
    }

    public String Listar_EmpleadoLogistica(){
        String Output = "";
        for(EmpleadoLogistica_Model empleadoLog : empleadoLogisticaRepository.findAll()){
            Output += "Id: "+empleadoLog.getIdLogistica() + "\n";
            Output += "Nombre: "+empleadoLog.getNombre() + "\n";
            Output += "Email: "+empleadoLog.getEmail() + "\n";
            Output += "Password: "+empleadoLog.getPassword() + "\n";
            Output += "Tienda: "+empleadoLog.getTiendaAsignada() + "\n";
        }
        if (Output.isEmpty()){
            return "No hay empleados de Logistica";
        }else  {
            return Output;
        }
    }

    public String obtener_EmpleadoLogisticaId(int id){
        String Output = "";
        if(empleadoLogisticaRepository.existsById(id)){
            EmpleadoLogistica_Model empleadoLog = empleadoLogisticaRepository.findById(id).get();
            Output += "Id: "+empleadoLog.getIdLogistica() + "\n";
            Output += "Nombre: "+empleadoLog.getNombre() + "\n";
            Output += "Email: "+empleadoLog.getEmail() + "\n";
            Output += "Password: "+empleadoLog.getPassword() + "\n";
            Output += "Tienda: "+empleadoLog.getTiendaAsignada() + "\n";
            return Output;
        }else  {
            return "Empleado de Logistica no encontrado";
        }
    }

    public String actualizar_EmpleadoLogistica(int id, EmpleadoLogistica_Model EmpleadoLogistica){
        if(empleadoLogisticaRepository.existsById(id)){
            EmpleadoLogistica_Model EmpleadoLogisticaB = empleadoLogisticaRepository.findById(id).get();
            EmpleadoLogistica.setNombre(EmpleadoLogisticaB.getNombre());
            EmpleadoLogistica.setEmail(EmpleadoLogisticaB.getEmail());
            EmpleadoLogistica.setPassword(EmpleadoLogisticaB.getPassword());
            empleadoLogisticaRepository.save(EmpleadoLogisticaB);
            return "Empleado de Logistica actualizado con exito";
        }else  {
            return "Empleado de Logistica no encontrado";
        }
    }
}
