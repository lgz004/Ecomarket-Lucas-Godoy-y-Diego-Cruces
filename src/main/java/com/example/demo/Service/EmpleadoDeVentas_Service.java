package com.example.demo.Service;

import com.example.demo.Model.EmpleadoDeVentas_Model;

import com.example.demo.Repository.EmpleadoDeVentas_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmpleadoDeVentas_Service {
    @Autowired
    EmpleadoDeVentas_Repository empleadoDeVentas_Repository;

    public String agregar_EmpleadoDeVentas(EmpleadoDeVentas_Model EmpleadoDeVentas){
        empleadoDeVentas_Repository.save(EmpleadoDeVentas);
        return "Empleado de ventas agregado con exito";
    }

    public String eliminar_EmpleadoDeVentas(int id){
        if(empleadoDeVentas_Repository.existsById(id)){
            empleadoDeVentas_Repository.deleteById(id);
            return "Empleado de ventas eliminado con exito";
        }else {
            return "Empleado de ventas no encontrado";
        }
    }

    public String Listar_EmpleadoDeVentas(){
        String Output = "";
        for(EmpleadoDeVentas_Model empleadoDeVentas : empleadoDeVentas_Repository.findAll()){
            Output += "Id: "+empleadoDeVentas.getIdEmpleado() + "\n";
            Output += "Nombre: "+empleadoDeVentas.getNombre() + "\n";
            Output += "Email: "+empleadoDeVentas.getEmail() + "\n";
            Output += "Password: "+empleadoDeVentas.getPassword() + "\n";
            Output += "Tienda: "+empleadoDeVentas.getTienda() + "\n";
        }
        if (Output.isEmpty()){
            return "No hay empleados de ventas";
        }else  {
            return Output;
        }
    }

    public String obtener_EmpleadoDeVentas(int id){
        String Output = "";
        if(empleadoDeVentas_Repository.existsById(id)){
            EmpleadoDeVentas_Model EmpleadoDeVentas = empleadoDeVentas_Repository.findById(id).get();
            Output += "Id: "+EmpleadoDeVentas.getIdEmpleado() + "\n";
            Output += "Nombre: "+EmpleadoDeVentas.getNombre() + "\n";
            Output += "Email: "+EmpleadoDeVentas.getEmail() + "\n";
            Output += "Password: "+EmpleadoDeVentas.getPassword() + "\n";
            Output += "Tienda: "+EmpleadoDeVentas.getTienda() + "\n";
            return Output;
        }else  {
            return "Empleado de ventas no encontrado";
        }
    }

    public String actualizar_EmpleadoDeVentas(int id, EmpleadoDeVentas_Model EmpleadoDeVentas){
        if(empleadoDeVentas_Repository.existsById(id)){
            EmpleadoDeVentas_Model EmpleadoDeVentasB = empleadoDeVentas_Repository.findById(id).get();
            EmpleadoDeVentas.setNombre(EmpleadoDeVentasB.getNombre());
            EmpleadoDeVentas.setEmail(EmpleadoDeVentasB.getEmail());
            EmpleadoDeVentas.setPassword(EmpleadoDeVentasB.getPassword());
            empleadoDeVentas_Repository.save(EmpleadoDeVentasB);
            return "Empleado de ventas actualizado con exito";
        }else  {
            return "Empleado de ventas no encontrado";
        }
    }
}
