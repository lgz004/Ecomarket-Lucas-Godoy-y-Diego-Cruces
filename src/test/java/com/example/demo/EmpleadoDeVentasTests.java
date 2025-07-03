package com.example.demo;

import com.example.demo.Controller.EmpleadoDeVentas_Controller;
import com.example.demo.Model.EmpleadoDeVentas_Model;
import com.example.demo.Model.Usuario_Model;
import com.example.demo.Repository.EmpleadoDeVentas_Repository;
import com.example.demo.Repository.Pedido_Repository;
import com.example.demo.Service.EmpleadoDeVentas_Service;
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
public class EmpleadoDeVentasTests {

    @Mock
    EmpleadoDeVentas_Repository empleadoVentasRepository;

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    EmpleadoDeVentas_Service empleadoVentasServiceMock;

    @Test
    @DisplayName("FindAll Test")
    void testEmpleadoVentasServiceMock(){
        EmpleadoDeVentas_Model empleado = new EmpleadoDeVentas_Model();
        when(empleadoVentasRepository.findAll()).thenReturn(Arrays.asList(empleado));
        List<EmpleadoDeVentas_Model> empleados = empleadoVentasRepository.findAll();
        assertNotNull(empleados);
        assertEquals(1, empleados.size());
    }

    @Test
    @DisplayName("Rectificar nombre empleado de ventas")
    void testFindEmpleadoVentas(){
        EmpleadoDeVentas_Model test = new EmpleadoDeVentas_Model();
        test.setNombre("Carlos");
        when(empleadoVentasRepository.findById(1)).thenReturn(Optional.of(test));
        EmpleadoDeVentas_Model result = empleadoVentasRepository.findById(1).get();
        assertNotNull(result);
        assertEquals("Carlos", result.getNombre());
    }

    @Test
    @DisplayName("Actualizar nombre de empleado de ventas")
    void testUpdateEmpleadoVentas() {
        EmpleadoDeVentas_Model empleado = new EmpleadoDeVentas_Model();
        empleado.setNombre("Carlos");
        when(empleadoVentasRepository.findById(1)).thenReturn(Optional.of(empleado));
        EmpleadoDeVentas_Model result = empleadoVentasRepository.findById(1).get();
        assertNotNull(result);
        result.setNombre("Agustin");
        when(empleadoVentasRepository.save(result)).thenReturn(result);
        when(empleadoVentasRepository.findById(1)).thenReturn(Optional.of(result));
        EmpleadoDeVentas_Model empleadoActualizado = empleadoVentasRepository.findById(1).get();
        assertEquals("Agustin", empleadoActualizado.getNombre());
    }

    @Test
    @DisplayName("Eliminar empleado de ventas existente")
    void testDeleteEmpleadoVentasById() {
        EmpleadoDeVentas_Model empleado = new EmpleadoDeVentas_Model();
        empleado.setIdEmpleado(1);
        when(empleadoVentasRepository.findById(1)).thenReturn(Optional.of(empleado));

        Optional<EmpleadoDeVentas_Model> result = empleadoVentasRepository.findById(1);
        assertTrue(result.isPresent());

        empleadoVentasRepository.deleteById(1);

        verify(empleadoVentasRepository, times(1)).deleteById(1);
    }
}
