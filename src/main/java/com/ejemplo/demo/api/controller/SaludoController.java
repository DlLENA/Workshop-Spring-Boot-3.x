package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.api.dto.SaludoRequest;
import com.ejemplo.demo.api.dto.SaludoResponse;
import com.ejemplo.demo.api.dto.WorkshopResponse;
import com.ejemplo.demo.api.interfaces.WorkshopApi;
import com.ejemplo.demo.domain.service.SaludoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaludoController implements WorkshopApi {

    private final SaludoService saludoService;

    public SaludoController(SaludoService saludoService) {
        this.saludoService = saludoService;
    }

    @Override
    public ResponseEntity<WorkshopResponse> getWorkshopHealth() {
        return ResponseEntity.ok(new WorkshopResponse("ok", "Workshop Spring Boot activo"));
    }

    @Override
    public ResponseEntity<SaludoResponse> saludarPorGet(String nombre) {
        return ResponseEntity.ok(saludoService.crearSaludo(nombre));
    }

    @Override
    public ResponseEntity<SaludoResponse> saludarPorPost(SaludoRequest request) {
        return ResponseEntity.ok(saludoService.crearSaludo(request.nombre()));
    }
}
