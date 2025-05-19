package com.example.demo.Repository;

import com.example.demo.Model.Producto_Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Producto_Repository extends JpaRepository<Producto_Model,Integer> {

}
