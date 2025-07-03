package com.example.demo;

import com.example.demo.Controller.GerenteTienda_Controller;
import com.example.demo.Model.GerenteTienda_Model;
import com.example.demo.Model.Usuario_Model;
import com.example.demo.Repository.GerenteTienda_Repository;
import com.example.demo.Repository.Pedido_Repository;
import com.example.demo.Service.GerenteTienda_Service;
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
public class GerenteTiendaTests {

    @Mock
    GerenteTienda_Repository gerenteTiendaRepository;

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    GerenteTienda_Service gerenteTiendaServiceMock;

    @Test
    @DisplayName("FindAll Test")
    void testGerenteTiendaServiceMock(){
        GerenteTienda_Model gerente = new GerenteTienda_Model();
        when(gerenteTiendaRepository.findAll()).thenReturn(Arrays.asList(gerente));
        List<GerenteTienda_Model> gerentes = gerenteTiendaRepository.findAll();
        assertNotNull(gerentes);
        assertEquals(1, gerentes.size());
    }

    @Test
    @DisplayName("Rectificar nombre gerente")
    void testFindGerenteTienda(){
        GerenteTienda_Model test = new GerenteTienda_Model();
        test.setNombre("Carlos");
        when(gerenteTiendaRepository.findById(1)).thenReturn(Optional.of(test));
        GerenteTienda_Model result = gerenteTiendaRepository.findById(1).get();
        assertNotNull(result);
        assertEquals("Carlos", result.getNombre());
    }

    @Test
    @DisplayName("Actualizar nombre de gerente")
    void testUpdateGerenteTienda() {
        GerenteTienda_Model gerente = new GerenteTienda_Model();
        gerente.setNombre("Carlos");
        when(gerenteTiendaRepository.findById(1)).thenReturn(Optional.of(gerente));
        GerenteTienda_Model result = gerenteTiendaRepository.findById(1).get();
        assertNotNull(result);
        result.setNombre("Agustin");
        when(gerenteTiendaRepository.save(result)).thenReturn(result);
        when(gerenteTiendaRepository.findById(1)).thenReturn(Optional.of(result));
        GerenteTienda_Model gerenteActualizado = gerenteTiendaRepository.findById(1).get();
        assertEquals("Agustin", gerenteActualizado.getNombre());
    }

    @Test
    @DisplayName("Eliminar gerente existente")
    void testDeleteGerenteTiendaById() {
        GerenteTienda_Model usuario = new GerenteTienda_Model();
        usuario.setIdGerente(1);
        when(gerenteTiendaRepository.findById(1)).thenReturn(Optional.of(usuario));

        Optional<GerenteTienda_Model> result = gerenteTiendaRepository.findById(1);
        assertTrue(result.isPresent());

        gerenteTiendaRepository.deleteById(1);

        verify(gerenteTiendaRepository, times(1)).deleteById(1);
    }

}
