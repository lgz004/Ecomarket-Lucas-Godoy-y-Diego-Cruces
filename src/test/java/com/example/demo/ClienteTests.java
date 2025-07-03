package com.example.demo;

import com.example.demo.Controller.Cliente_Controller;
import com.example.demo.Model.Cliente_Model;
import com.example.demo.Model.Usuario_Model;
import com.example.demo.Repository.Cliente_Repository;
import com.example.demo.Repository.Usuario_Repository;
import com.example.demo.Service.Cliente_Service;
import com.example.demo.Service.Usuario_Service;
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
public class ClienteTests {

    @Mock
    Cliente_Repository clienteRepository;

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    Cliente_Service clienteServiceMock;

    @Test
    @DisplayName("FindAll Test")
    void testClienteServiceMock(){
        Cliente_Model cliente = new Cliente_Model();
        when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente));
        List<Cliente_Model> clientes = clienteRepository.findAll();
        assertNotNull(clientes);
        assertEquals(1, clientes.size());
    }

    @Test
    @DisplayName("Rectificar nombre cliente")
    void testFindCliente(){
        Cliente_Model test = new Cliente_Model();
        test.setNombre("Carlos");
        when(clienteRepository.findById(1)).thenReturn(Optional.of(test));
        Cliente_Model result = clienteRepository.findById(1).get();
        assertNotNull(result);
        assertEquals("Carlos", result.getNombre());
    }

    @Test
    @DisplayName("Actualizar nombre de cliente")
    void testUpdateCliente() {
        Cliente_Model cliente = new Cliente_Model();
        cliente.setNombre("Carlos");
        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));
        Cliente_Model result = clienteRepository.findById(1).get();
        assertNotNull(result);
        result.setNombre("Agustin");
        when(clienteRepository.save(result)).thenReturn(result);
        when(clienteRepository.findById(1)).thenReturn(Optional.of(result));
        Cliente_Model clienteActualizado = clienteRepository.findById(1).get();
        assertEquals("Agustin", clienteActualizado.getNombre());
    }

    @Test
    @DisplayName("Eliminar cliente existente")
    void testDeleteClienteById() {
        Cliente_Model cliente = new Cliente_Model();
        cliente.setIdCliente(1);
        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));

        Optional<Cliente_Model> result = clienteRepository.findById(1);
        assertTrue(result.isPresent());

        clienteRepository.deleteById(1);

        verify(clienteRepository, times(1)).deleteById(1);
    }
}
