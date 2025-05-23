package com.example.demo.Service;

import com.example.demo.Model.EmpleadoDeVentas_Model;
import com.example.demo.Repository.EmpleadoDeVentas_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmpleadoDeVentas_Service {
    @Autowired
    EmpleadoDeVentas_Repository empleadoDeVentas_Repository;

    public String agregar_EmpleadoDeVentas(EmpleadoDeVentas_Model empleadoVentas){
        empleadoDeVentas_Repository.save(empleadoVentas);
        return "Empleado de Ventas agregado con exito";
    }

    public String eliminar_EmpleadoDeVentas(int id){
        if(empleadoDeVentas_Repository.existsById(id)){
            empleadoDeVentas_Repository.deleteById(id);
            return "Empleado de Ventas eliminado con exito";
        }else {
            return "Empleado de Ventas no encontrado";
        }
    }

    public String Listar_EmpleadoDeVentas(){
        String Output = "";
        for(EmpleadoDeVentas_Model empleadoVentas : empleadoDeVentas_Repository.findAll()){
            Output += "Id: "+empleadoVentas.getIdEmpleado() + "\n";
            Output += "Nombre: "+empleadoVentas.getNombre() + "\n";
            Output += "Email: "+empleadoVentas.getEmail() + "\n";
            Output += "Password: "+empleadoVentas.getPassword() + "\n";
            Output += "Tienda: "+empleadoVentas.getTienda() + "\n";
        }
        if (Output.isEmpty()){
            return "No hay Empleados de Ventas de Sistema";
        }else  {
            return Output;
        }
    }

    public String obtener_EmpleadoDeVentasId(int id){
        String Output = "";
        if(empleadoDeVentas_Repository.existsById(id)){
            EmpleadoDeVentas_Model empleadoVentas = empleadoDeVentas_Repository.findById(id).get();
            Output += "Id: "+empleadoVentas.getIdEmpleado() + "\n";
            Output += "Nombre: "+empleadoVentas.getNombre() + "\n";
            Output += "Email: "+empleadoVentas.getEmail() + "\n";
            Output += "Password: "+empleadoVentas.getPassword() + "\n";
            Output += "Tienda: "+empleadoVentas.getTienda() + "\n";
            return Output;
        }else  {
            return "Empleado de Ventas no encontrado";
        }
    }

    public String actualizar_EmpleadoDeVentas(int id, EmpleadoDeVentas_Model EmpleadoDeVentas){
        if(empleadoDeVentas_Repository.existsById(id)){
            EmpleadoDeVentas_Model empleadoDeVentasB = empleadoDeVentas_Repository.findById(id).get();
            empleadoDeVentasB.setNombre(EmpleadoDeVentas.getNombre());
            empleadoDeVentasB.setEmail(EmpleadoDeVentas.getEmail());
            empleadoDeVentasB.setPassword(EmpleadoDeVentas.getPassword());
            empleadoDeVentasB.setTienda(EmpleadoDeVentas.getTienda());
            empleadoDeVentas_Repository.save(empleadoDeVentasB);
            return "Empleado de Ventas actualizado con exito";
        }else  {
            return "Empleado de Ventas no encontrado";
        }
    }
}
