package com.example.demo.Controller;

import com.example.demo.Model.GerenteTienda_Model;
import com.example.demo.Service.GerenteTienda_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gerentes")
public class GerenteTienda_Controller {

    @Autowired
    private GerenteTienda_Service gerenteTienda_Service;

    @GetMapping
    public String getGerentes() {
        return gerenteTienda_Service.Listar_Gerentes();
    }

    @PostMapping
    public String addGerente(@RequestBody GerenteTienda_Model gerente) {
        return gerenteTienda_Service.agregar_Gerente(gerente);
    }

    @GetMapping("/{id}")
    public String getGerenteById(@PathVariable int id) {
        return gerenteTienda_Service.obtener_GerenteId(id);
    }

    @DeleteMapping("/{id}")
    public String deleteGerente(@PathVariable int id) {
        return gerenteTienda_Service.eliminar_Gerente(id);
    }

    @PutMapping("/{id}")
    public String editGerente(@PathVariable int id, @RequestBody GerenteTienda_Model gerente) {
        return gerenteTienda_Service.actualizar_Gerente(id, gerente);
    }
}
