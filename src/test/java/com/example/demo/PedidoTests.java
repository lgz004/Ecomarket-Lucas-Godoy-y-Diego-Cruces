package com.example.demo;

import com.example.demo.Controller.Pedido_Controller;
import com.example.demo.Model.Pedido_Model;
import com.example.demo.Repository.Pedido_Repository;
import com.example.demo.Service.Pedido_Service;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PedidoTests {

    @Autowired
    Pedido_Repository pedidoRepository;

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    Pedido_Service pedidoServiceMock;

    @Test
    @DisplayName("FindAll Test")
    void testPedidoServiceMock() {
        List<Pedido_Model> pedidos = pedidoRepository.findAll();
        assertNotNull(pedidos);
        assertEquals(1, pedidos.size());
    }

    @Test
    @DisplayName("Rectificar precio producto")
    void testFindPedido(){
        Pedido_Model prueba = pedidoRepository.findById(1).get();
        assertNotNull(prueba);
        assertEquals(400.0,prueba.getTotalPedido());
    }

    @Test
    @DisplayName("Test controller")
    void testController()  {
        //Indicamos que el retorno de listarPedidos se identificara con el valor ingresado en thenReturn
        when(pedidoServiceMock.listarPedidos()).thenReturn("Lista completa");

        //Bloque Try Except/Catch
        //Nos permite probar una funcionalidad de codigo o un segmento de codigo y si este falla
        //Se captura por medio de Catch(Exception var) y ejecuta un control de error
        try{
            //MockMvc Nos permite realizar consultas HTTPMethod
            //perform nos permite ejecutar dichas llamadas y luego ingresamos el metodo HTTP correspondiente
            //adicionalmente podemos agregar parametros u variables adicionales de ser requerido
            //Finalmente andExcept nos permite indicar que esperamos de dicha respuesta HTTP
            //Tanto codigo como contenido
            mockMvc.perform(get("/pedidos"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Lista completa"));

        }catch(Exception ex){
            System.out.println(ex.getMessage());
            fail();
        }
    }
}
