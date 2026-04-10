package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.api.dto.PrestamoRequest;
import com.ejemplo.demo.api.dto.PrestamoResponse;
import com.ejemplo.demo.domain.service.PrestamoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/simulaciones")//esta sera la nueva ruta base
public class PrestamoController {
	
	private final PrestamoService prestamoService;
	
	//inyectamos el servicio matematico donde se encuentran los calculos 
	public PrestamoController(PrestamoService prestamoService) {
		this.prestamoService = prestamoService;
	}
	
	@PostMapping("/prestamo") 
	public ResponseEntity<PrestamoResponse> simularPrestamo(@Valid @RequestBody PrestamoRequest request){
		
		PrestamoResponse respuesta = prestamoService.simularPrestamo(
				request.monto(),
				request.tasaAnual(),
				request.meses()
				
	  );
		
		return ResponseEntity.ok(respuesta);
    }
}
