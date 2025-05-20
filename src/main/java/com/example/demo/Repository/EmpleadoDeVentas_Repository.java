package com.example.demo.Repository;

import com.example.demo.Model.EmpleadoDeVentas_Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmpleadoDeVentas_Repository extends JpaRepository<EmpleadoDeVentas_Model,Integer> {
}
