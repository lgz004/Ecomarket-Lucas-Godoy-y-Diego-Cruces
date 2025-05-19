package com.example.demo.Repository;

import com.example.demo.Model.Cliente_Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Cliente_Repository extends JpaRepository<Cliente_Model, Integer> {

}
