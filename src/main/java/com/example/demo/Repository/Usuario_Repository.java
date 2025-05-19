package com.example.demo.Repository;

import com.example.demo.Model.Usuario_Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface Usuario_Repository extends JpaRepository<Usuario_Model,Integer> {

}
