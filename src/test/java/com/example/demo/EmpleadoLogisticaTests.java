package com.example.demo;

import com.example.demo.Controller.EmpleadoLogistica_Controller;
import com.example.demo.Model.EmpleadoLogistica_Model;
import com.example.demo.Model.Usuario_Model;
import com.example.demo.Repository.EmpleadoLogistica_Repository;
import com.example.demo.Repository.Pedido_Repository;
import com.example.demo.Service.EmpleadoLogistica_Service;
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
public class EmpleadoLogisticaTests {

    @Mock
    EmpleadoLogistica_Repository empleadoLogisticaRepository;

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    EmpleadoLogistica_Service empleadoLogisticaServiceMock;

    @Test
    @DisplayName("FindAll Test")
    void testEmpleadoLogisticaServiceMock(){
        EmpleadoLogistica_Model empleado = new EmpleadoLogistica_Model();
        when(empleadoLogisticaRepository.findAll()).thenReturn(Arrays.asList(empleado));
        List<EmpleadoLogistica_Model> empleados = empleadoLogisticaRepository.findAll();
        assertNotNull(empleados);
        assertEquals(1, empleados.size());
    }

    @Test
    @DisplayName("Rectificar nombre empleado logistica")
    void testFindEmpleadoLogistica(){
        EmpleadoLogistica_Model test = new EmpleadoLogistica_Model();
        test.setNombre("Carlos");
        when(empleadoLogisticaRepository.findById(1)).thenReturn(Optional.of(test));
        EmpleadoLogistica_Model result = empleadoLogisticaRepository.findById(1).get();
        assertNotNull(result);
        assertEquals("Carlos", result.getNombre());
    }

    @Test
    @DisplayName("Actualizar nombre de emplead logistica")
    void testUpdateEmpleadoLogistica() {
        EmpleadoLogistica_Model empleado = new EmpleadoLogistica_Model();
        empleado.setNombre("Carlos");
        when(empleadoLogisticaRepository.findById(1)).thenReturn(Optional.of(empleado));
        EmpleadoLogistica_Model result = empleadoLogisticaRepository.findById(1).get();
        assertNotNull(result);
        result.setNombre("Agustin");
        when(empleadoLogisticaRepository.save(result)).thenReturn(result);
        when(empleadoLogisticaRepository.findById(1)).thenReturn(Optional.of(result));
        EmpleadoLogistica_Model empleadoActualizado = empleadoLogisticaRepository.findById(1).get();
        assertEquals("Agustin", empleadoActualizado.getNombre());
    }

    @Test
    @DisplayName("Eliminar empleado logistica existente")
    void testDeleteEmpleadoLogisticaById() {
        EmpleadoLogistica_Model empleado = new EmpleadoLogistica_Model();
        empleado.setIdLogistica(1);
        when(empleadoLogisticaRepository.findById(1)).thenReturn(Optional.of(empleado));

        Optional<EmpleadoLogistica_Model> result = empleadoLogisticaRepository.findById(1);
        assertTrue(result.isPresent());

        empleadoLogisticaRepository.deleteById(1);

        verify(empleadoLogisticaRepository, times(1)).deleteById(1);
    }
}
