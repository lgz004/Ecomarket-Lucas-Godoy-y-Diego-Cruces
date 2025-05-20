package com.example.demo.Controller;

import com.example.demo.Model.EmpleadoLogistica_Model;
import com.example.demo.Service.EmpleadoLogistica_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empleadosLogistica")
public class EmpleadoLogistica_Controller {

    @Autowired
    private EmpleadoLogistica_Service empleadoLogistica_Service;

    @GetMapping
    public String getEmpleadosLogistica(){
        return empleadoLogistica_Service.Listar_EmpleadoLogistica();
    }

    @PostMapping
    public String addEmpleadoLogistica(@RequestBody EmpleadoLogistica_Model empleadoLogistica){
        return empleadoLogistica_Service.agregar_EmpleadoLogistica(empleadoLogistica);
    }

    @GetMapping("/{id}")
    public String getEmpleadoLogisticaId(@PathVariable int id){
        return empleadoLogistica_Service.obtener_EmpleadoLogisticaId(id);
    }

    @DeleteMapping("/{id}")
    public String eliminarEmpleadoLogistica(@PathVariable int id){
        return empleadoLogistica_Service.eliminar_EmpleadoLogistica(id);
    }

    @PutMapping("/{id}")
    public String editarEmpleadoLogistica(@PathVariable int id, @RequestBody EmpleadoLogistica_Model empleadoLogistica){
        return empleadoLogistica_Service.actualizar_EmpleadoLogistica(id, empleadoLogistica);
    }
}
