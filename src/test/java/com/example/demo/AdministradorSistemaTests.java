package com.example.demo;

import com.example.demo.Controller.AdministradorSistema_Controller;
import com.example.demo.Model.AdministradorSistema_Model;
import com.example.demo.Model.Usuario_Model;
import com.example.demo.Repository.AdministradorSistema_Repository;
import com.example.demo.Repository.Pedido_Repository;
import com.example.demo.Service.AdministradorSistema_Service;
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
public class AdministradorSistemaTests {

    @Mock
    AdministradorSistema_Repository administradorSistemaRepository;

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    AdministradorSistema_Service pedidoServiceMock;

    @Test
    @DisplayName("FindAll Test")
    void testAdministradorSistemaServiceMock(){
        AdministradorSistema_Model administrador = new AdministradorSistema_Model();
        when(administradorSistemaRepository.findAll()).thenReturn(Arrays.asList(administrador));
        List<AdministradorSistema_Model> administradores = administradorSistemaRepository.findAll();
        assertNotNull(administradores);
        assertEquals(1, administradores.size());
    }

    @Test
    @DisplayName("Rectificar nombre administrador")
    void testFindAdministradorSistema(){
        AdministradorSistema_Model test = new AdministradorSistema_Model();
        test.setNombre("Carlos");
        when(administradorSistemaRepository.findById(1)).thenReturn(Optional.of(test));
        AdministradorSistema_Model result = administradorSistemaRepository.findById(1).get();
        assertNotNull(result);
        assertEquals("Carlos", result.getNombre());
    }

    @Test
    @DisplayName("Actualizar nombre de administrador sistema")
    void testUpdateAdministradorSistema() {
        AdministradorSistema_Model administrador = new AdministradorSistema_Model();
        administrador.setNombre("Carlos");
        when(administradorSistemaRepository.findById(1)).thenReturn(Optional.of(administrador));
        AdministradorSistema_Model result = administradorSistemaRepository.findById(1).get();
        assertNotNull(result);
        result.setNombre("Agustin");
        when(administradorSistemaRepository.save(result)).thenReturn(result);
        when(administradorSistemaRepository.findById(1)).thenReturn(Optional.of(result));
        AdministradorSistema_Model administradorActualizado = administradorSistemaRepository.findById(1).get();
        assertEquals("Agustin", administradorActualizado.getNombre());
    }

    @Test
    @DisplayName("Eliminar administrador sistema existente")
    void testDeleteAdministradorSistemaById() {
        AdministradorSistema_Model administrador = new AdministradorSistema_Model();
        administrador.setIdAdmin(1);
        when(administradorSistemaRepository.findById(1)).thenReturn(Optional.of(administrador));

        Optional<AdministradorSistema_Model> result = administradorSistemaRepository.findById(1);
        assertTrue(result.isPresent());

        administradorSistemaRepository.deleteById(1);

        verify(administradorSistemaRepository, times(1)).deleteById(1);
    }

}
