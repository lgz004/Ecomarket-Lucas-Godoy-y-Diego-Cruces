package com.example.demo;

import com.example.demo.Controller.Pedido_Controller;
import com.example.demo.Model.Pedido_Model;
import com.example.demo.Repository.Pedido_Repository;
import com.example.demo.Service.Pedido_Service;
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
public class PedidoTests {

    @Mock
    Pedido_Repository pedidoRepository;

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    Pedido_Service pedidoServiceMock;

    @Test
    @DisplayName("FindAll Test")
    void testPedidoServiceMock(){
        Pedido_Model pedido = new Pedido_Model();
        when(pedidoRepository.findAll()).thenReturn(Arrays.asList(pedido));
        List<Pedido_Model> pedidos = pedidoRepository.findAll();
        assertNotNull(pedidos);
        assertEquals(1, pedidos.size());
    }

    @Test
    @DisplayName("Rectificar precio pedido")
    void testFindPedido(){
        Pedido_Model test = new Pedido_Model();
        test.setTotalPedido(400.0);
        when(pedidoRepository.findById(1)).thenReturn(Optional.of(test));
        Pedido_Model result = pedidoRepository.findById(1).get();
        assertNotNull(result);
        assertEquals(400.0, result.getTotalPedido());
    }

    @Test
    @DisplayName("Actualizar precio total de pedido")
    void testUpdatePedido() {
        Pedido_Model pedido = new Pedido_Model();
        pedido.setTotalPedido(800.0);
        when(pedidoRepository.findById(1)).thenReturn(Optional.of(pedido));
        Pedido_Model result = pedidoRepository.findById(1).get();
        assertNotNull(result);
        result.setTotalPedido(1000.0);
        when(pedidoRepository.save(result)).thenReturn(result);
        when(pedidoRepository.findById(1)).thenReturn(Optional.of(result));
        Pedido_Model pedidoActualizado = pedidoRepository.findById(1).get();
        assertEquals(1000.0, pedidoActualizado.getTotalPedido());
    }

    @Test
    @DisplayName("Eliminar pedido existente")
    void testDeletePedidoById() {

        Pedido_Model pedido = new Pedido_Model();
        pedido.setIdPedido(1);
        when(pedidoRepository.findById(1)).thenReturn(Optional.of(pedido));

        Optional<Pedido_Model> result = pedidoRepository.findById(1);
        assertTrue(result.isPresent());

        pedidoRepository.deleteById(1);

        verify(pedidoRepository, times(1)).deleteById(1);
    }

}
