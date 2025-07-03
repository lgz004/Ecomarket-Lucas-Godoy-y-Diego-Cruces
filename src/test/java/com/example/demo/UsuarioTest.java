package com.example.demo;



import com.example.demo.Controller.Usuario_Controller;
import com.example.demo.Model.Pedido_Model;
import com.example.demo.Model.Usuario_Model;
import com.example.demo.Repository.Pedido_Repository;
import com.example.demo.Repository.Usuario_Repository;
import com.example.demo.Service.Pedido_Service;
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

public class UsuarioTest {

    @Mock
    Usuario_Repository usuarioRepository;

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    Usuario_Service usuarioServiceMock;

    @Test
    @DisplayName("FindAll Test")
    void testUsuarioServiceMock(){
        Usuario_Model usuario = new Usuario_Model();
        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(usuario));
        List<Usuario_Model> usuarios = usuarioRepository.findAll();
        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());
    }

    @Test
    @DisplayName("Rectificar nombre usuario")
    void testFindUsuario(){
        Usuario_Model test = new Usuario_Model();
        test.setNombre("Carlos");
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(test));
        Usuario_Model result = usuarioRepository.findById(1).get();
        assertNotNull(result);
        assertEquals("Carlos", result.getNombre());
    }

    @Test
    @DisplayName("Actualizar nombre de usuario")
    void testUpdateUsuario() {
        Usuario_Model usuario = new Usuario_Model();
        usuario.setNombre("Carlos");
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
        Usuario_Model result = usuarioRepository.findById(1).get();
        assertNotNull(result);
        result.setNombre("Agustin");
        when(usuarioRepository.save(result)).thenReturn(result);
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(result));
        Usuario_Model usuarioActualizado = usuarioRepository.findById(1).get();
        assertEquals("Agustin", usuarioActualizado.getNombre());
    }

    @Test
    @DisplayName("Eliminar usuario existente")
    void testDeleteUsuarioById() {
        Usuario_Model usuario = new Usuario_Model();
        usuario.setIdUsuario(1);
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        Optional<Usuario_Model> result = usuarioRepository.findById(1);
        assertTrue(result.isPresent());

        usuarioRepository.deleteById(1);

        verify(usuarioRepository, times(1)).deleteById(1);
    }

}
