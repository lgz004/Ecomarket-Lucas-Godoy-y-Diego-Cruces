package com.example.demo.Repository;

import com.example.demo.Model.Producto_Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.ArrayList;

@Repository
public interface Producto_Repository extends JpaRepository<Producto_Model,Integer> {

}
