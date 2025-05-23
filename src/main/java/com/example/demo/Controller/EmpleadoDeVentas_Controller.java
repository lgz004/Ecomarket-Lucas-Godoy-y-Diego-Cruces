package com.example.demo.Controller;

import com.example.demo.Model.EmpleadoDeVentas_Model;
import com.example.demo.Service.EmpleadoDeVentas_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/EmpleadosDeVentas")
public class EmpleadoDeVentas_Controller {
    @Autowired
    private EmpleadoDeVentas_Service empleadoDeVentasService;

    @GetMapping
    public String getEmpleadosDeVentas() {
        return empleadoDeVentasService.Listar_EmpleadoDeVentas();
    }

    @PostMapping
    public String addEmpleadoDeVentas(@RequestBody EmpleadoDeVentas_Model EmpleadoDeVentas) {
        return empleadoDeVentasService.agregar_EmpleadoDeVentas(EmpleadoDeVentas);
    }

    @GetMapping("/{id}")
    public String getEmpleadoDeVentasById(@PathVariable int id) {
        return empleadoDeVentasService.obtener_EmpleadoDeVentasId(id);
    }

    @DeleteMapping("/{id}")
    public String eliminarEmpleadoDeVentas(@PathVariable int id) {
        return empleadoDeVentasService.eliminar_EmpleadoDeVentas(id);
    }

    @PutMapping("/{id}")
    public String editarEmpleadoDeVentas(@PathVariable int id, @RequestBody EmpleadoDeVentas_Model EmpleadoDeVentas) {
        return empleadoDeVentasService.actualizar_EmpleadoDeVentas(id, EmpleadoDeVentas);
    }

}
