package com.example.demo.Repository;

import com.example.demo.Model.AdministradorSistema_Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorSistema_Repository extends JpaRepository<AdministradorSistema_Model, Integer> {
}
