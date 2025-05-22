package com.example.demo.Repository;

import com.example.demo.Model.Pedido_Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Pedido_Repository extends JpaRepository<Pedido_Model,Integer> {
}
