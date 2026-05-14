package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.api.dto.PrestamoRequest;
import com.ejemplo.demo.api.dto.PrestamoResponse;
import com.ejemplo.demo.api.interfaces.SimulacionesApi;
import com.ejemplo.demo.domain.service.PrestamoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrestamoController implements SimulacionesApi {
	
	private final PrestamoService prestamoService;
	
	//inyectamos el servicio matematico donde se encuentran los calculos 
	public PrestamoController(PrestamoService prestamoService) {
		this.prestamoService = prestamoService;
	}
	
	@Override
	public ResponseEntity<PrestamoResponse> simularPrestamo(PrestamoRequest request){
		
		PrestamoResponse respuesta = prestamoService.simularPrestamo(
				request.monto(),
				request.tasaAnual(),
				request.meses()
				
	  );
		
		return ResponseEntity.ok(respuesta);
    }
}
