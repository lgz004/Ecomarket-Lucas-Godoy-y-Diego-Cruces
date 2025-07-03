package com.example.demo;

import com.example.demo.Controller.Producto_Controller;
import com.example.demo.Model.Pedido_Model;
import com.example.demo.Model.Producto_Model;
import com.example.demo.Repository.Pedido_Repository;
import com.example.demo.Repository.Producto_Repository;
import com.example.demo.Service.Pedido_Service;
import com.example.demo.Service.Producto_Service;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;


@SpringBootTest
@AutoConfigureMockMvc
public class ProductoTest {

    @Mock
    Producto_Repository productoRepository;

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    Producto_Service productoServiceMock;

    @Test
    @DisplayName("FindAll Test")
    void testProductoServiceMock(){
        Producto_Model producto = new Producto_Model();
        when(productoRepository.findAll()).thenReturn(Arrays.asList(producto));
        List<Producto_Model> productos = productoRepository.findAll();
        assertNotNull(productos);
        assertEquals(1, productos.size());
    }

    @Test
    @DisplayName("Rectificar precio producto")
    void testFindProducto(){
        Producto_Model test = new Producto_Model();
        test.setPrecio(400.0);
        when(productoRepository.findById(1)).thenReturn(Optional.of(test));
        Producto_Model result = productoRepository.findById(1).get();
        assertNotNull(result);
        assertEquals(400.0, result.getPrecio());
    }

    @Test
    @DisplayName("Actualizar precio de producto")
    void testUpdateProducto() {
        Producto_Model producto = new Producto_Model();
        producto.setPrecio(800.0);
        when(productoRepository.findById(1)).thenReturn(Optional.of(producto));
        Producto_Model result = productoRepository.findById(1).get();
        assertNotNull(result);
        result.setPrecio(1000.0);
        when(productoRepository.save(result)).thenReturn(result);
        when(productoRepository.findById(1)).thenReturn(Optional.of(result));
        Producto_Model productoActualizado = productoRepository.findById(1).get();
        assertEquals(1000.0, productoActualizado.getPrecio());
    }

    @Test
    @DisplayName("Eliminar producto existente")
    void testDeleteProductoById() {

        Producto_Model producto = new Producto_Model();
        producto.setIdProducto(1);
        when(productoRepository.findById(1)).thenReturn(Optional.of(producto));

        Optional<Producto_Model> result = productoRepository.findById(1);
        assertTrue(result.isPresent());

        productoRepository.deleteById(1);

        verify(productoRepository, times(1)).deleteById(1);
    }
}
