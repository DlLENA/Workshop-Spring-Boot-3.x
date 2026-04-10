package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.api.dto.SaludoResponse;
import com.ejemplo.demo.domain.service.SaludoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SaludoController.class)
class SaludoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private SaludoService saludoService;

    @Test
    @DisplayName("Debe responder health del workshop")
    void debeResponderHealthDelWorkshop() throws Exception {
        mockMvc.perform(get("/api/v1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("ok"));
    }

    /*
    PASO 6 (EJERCICIO):
    Cuando habilites los endpoints de /api/v1/saludos, crea estas pruebas:

    1) GET /api/v1/saludos?nombre=Ana -> 200 y mensaje correcto
    2) POST /api/v1/saludos con {"nombre":""} -> 400 y codigo VALIDATION_ERROR
    */
    
    //PRUEBA DEL GET 
    @Test 
    @DisplayName("GET /api/v1/saludos?nombre=Ana -> 200 y mensaje correcto")
    void obtenerSaludoDeberiaRetornar200() throws Exception {
    	
    	when(saludoService.crearSaludo("Ana"))
    	.thenReturn(new SaludoResponse("Hola Ana, bienvenida al workshop!", null));
    	
    	mockMvc.perform(get("/api/v1/saludos")
    			.param("nombre", "Ana"))
    	        .andExpect(status().isOk())
    	        .andExpect(jsonPath("$.mensaje").exists()); //ve que el json tenga el campo de mensaje 
    }
    
    //PRUEBA DEL POST 
    @Test
    @DisplayName("POST /api/v1/saludos con nombre vacio -> 400 y VALIDACION_ERROR")
    void crearSaludoConNombreVacioDeberiaRetornar400() throws Exception {
    	
    	String jsonInvalido = "{\"nombre\":\"\"}";
    	
    	mockMvc.perform(post("/api/v1/saludos")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(jsonInvalido))
    	        .andExpect(status().isBadRequest()) //espera el http 400
    	        .andExpect(jsonPath("$.codigo").value("VALIDATION_ERROR")); //verifica que si da errores
    }
}
