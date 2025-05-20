package com.example.demo.Repository;

import com.example.demo.Model.EmpleadoLogistica_Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoLogistica_Repository extends JpaRepository<EmpleadoLogistica_Model, Integer> {

}
