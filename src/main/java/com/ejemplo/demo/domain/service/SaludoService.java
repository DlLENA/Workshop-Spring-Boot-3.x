package com.ejemplo.demo.domain.service;

import com.ejemplo.demo.api.dto.SaludoResponse;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class SaludoService {

    public SaludoResponse crearSaludo(String nombre) {
        String nombreNormalizado = normalizarNombre(nombre);
        String mensaje = "Hola, %s. Bienvenido a Spring Boot 3!".formatted(nombreNormalizado);
        return new SaludoResponse(mensaje, Instant.now());
    }

    /*
    PASO 4 (EJERCICIO):
    - Modifica esta logica para personalizar el formato del nombre.
    - Ideas:
      1) Primera letra mayuscula y resto minuscula.
      2) Rechazar nombres con numeros.
      3) Agregar prefijo "Estudiante".
    */
    String normalizarNombre(String nombre) {
    	//validar para que no sea nulo o existan espacios en blanco 
    	 if (nombre == null || nombre.trim().isEmpty()){
      	   return nombre;   
         }
      
      //1) primera letra mayuscula y resto minuscula 
      String nombreLimpio = nombre.trim();//quitar los espacios es blanco 
      
       String nombreFinal = nombreLimpio.substring(0, 1).toUpperCase() + nombreLimpio.substring(1).toLowerCase(); 
       
       if (nombreFinal.equals("Voldemort")) {
           throw new IllegalArgumentException("No se debe nombrar a este usuario");
       }
       
       return nombreFinal;

    }
}
      
