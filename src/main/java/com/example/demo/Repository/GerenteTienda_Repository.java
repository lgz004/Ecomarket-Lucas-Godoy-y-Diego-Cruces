package com.example.demo.Repository;

import com.example.demo.Model.GerenteTienda_Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GerenteTienda_Repository extends JpaRepository<GerenteTienda_Model,Integer> {
}
