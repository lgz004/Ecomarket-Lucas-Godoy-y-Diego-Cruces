package com.example.demo.Service;

import com.example.demo.Model.AdministradorSistema_Model;
import com.example.demo.Repository.AdministradorSistema_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorSistema_Service {
    @Autowired
    AdministradorSistema_Repository administradorSistemaRepository;

    public void agregar_AdministradorSistema(AdministradorSistema_Model administradorSistema) {
        administradorSistemaRepository.save(administradorSistema);
    }

    public void eliminar_AdministradorSistema(int id) {
        administradorSistemaRepository.deleteById(id);
    }

    public List<AdministradorSistema_Model> Listar_AdministradorSistema() {
        return administradorSistemaRepository.findAll();
    }

    public Optional<AdministradorSistema_Model> obtener_AdministradorSistemaId(int id) {
        return administradorSistemaRepository.findById(id);
    }

    public void actualizar_AdministradorSistema(int id, AdministradorSistema_Model administradorSistema) {
        AdministradorSistema_Model administradorSistema_actual = administradorSistemaRepository.findById(id).get();
        administradorSistema_actual.setNombre(administradorSistema.getNombre());
        administradorSistema_actual.setEmail(administradorSistema.getEmail());
        administradorSistema_actual.setPassword(administradorSistema.getPassword());
        administradorSistema_actual.setTienda(administradorSistema.getTienda());
        administradorSistemaRepository.save(administradorSistema_actual);
    }
}
