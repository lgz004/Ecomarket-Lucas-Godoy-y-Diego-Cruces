package com.example.demo.Service;

import com.example.demo.Model.GerenteTienda_Model;
import com.example.demo.Model.Pedido_Model;
import com.example.demo.Model.Usuario_Model;
import com.example.demo.Repository.GerenteTienda_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GerenteTienda_Service {

    @Autowired
    GerenteTienda_Repository gerenteTienda_Repository;

    public void agregarGerente(GerenteTienda_Model gerente) {
        gerenteTienda_Repository.save(gerente);
    }

    public void eliminarGerente(int id) {
        gerenteTienda_Repository.deleteById(id);
    }

    public List<GerenteTienda_Model> listarGerentes(){
        return gerenteTienda_Repository.findAll();
    }

    public Optional<GerenteTienda_Model> obtenerGerente(int id){
        return gerenteTienda_Repository.findById(id);
    }

    public void actualizarGerente(int id, GerenteTienda_Model gerente){
        GerenteTienda_Model gerente_actual = gerenteTienda_Repository.findById(id).get();
        gerente_actual.setNombre(gerente.getNombre());
        gerente_actual.setEmail(gerente.getEmail());
        gerente_actual.setPassword(gerente.getPassword());
        gerente_actual.setTiendaAsignada(gerente.getTiendaAsignada());
        gerenteTienda_Repository.save(gerente_actual);
    }
}
