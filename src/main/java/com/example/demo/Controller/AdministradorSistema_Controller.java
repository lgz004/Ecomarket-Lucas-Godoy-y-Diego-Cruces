package com.example.demo.Controller;

import com.example.demo.Model.AdministradorSistema_Model;
import com.example.demo.Service.AdministradorSistema_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/AdminsSistema")
public class AdministradorSistema_Controller {
    @Autowired
    private AdministradorSistema_Service administradorSistemaService;

    @GetMapping
    public String getAdministradoresSistema(){
        return administradorSistemaService.Listar_AdministradoresSistema();
    }

    @PostMapping
    public String addAdministradorSistema(@RequestBody AdministradorSistema_Model administradorSistema){
        return administradorSistemaService.agregar_AdministradorSistema(administradorSistema);
    }

    @GetMapping("/{id}")
    public String getAdministradorSistemaById(@PathVariable int id){
        return administradorSistemaService.obtener_AdministradorSistemaId(id);
    }

    @DeleteMapping("/{id}")
    public String deleteAdministradorSistemaById(@PathVariable int id){
        return administradorSistemaService.eliminar_AdministradorSistema(id);
    }

    @PutMapping("/{id}")
    public String editarAdministradorSistema(@PathVariable int id, @RequestBody AdministradorSistema_Model administradorSistema){
        return administradorSistemaService.actualizar_AdministradorSistema(id, administradorSistema);
    }
}
