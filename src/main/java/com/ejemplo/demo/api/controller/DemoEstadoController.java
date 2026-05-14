package com.ejemplo.demo.api.controller;

import com.ejemplo.demo.api.dto.EstadoResponse;
import com.ejemplo.demo.api.interfaces.DemoEstadoApi;
import com.ejemplo.demo.domain.service.EstadoManual;
import com.ejemplo.demo.domain.service.EstadoSingletonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoEstadoController implements DemoEstadoApi {

    private final EstadoSingletonService singletonService;

    public DemoEstadoController(EstadoSingletonService singletonService) {
        this.singletonService = singletonService;
    }

    @Override
    public ResponseEntity<EstadoResponse> actualizarSingleton(Integer valor) {
        singletonService.actualizarValor(valor);
        return ResponseEntity.ok(new EstadoResponse("Singleton", singletonService.obtenerValor()));
    }

    @Override
    public ResponseEntity<EstadoResponse> obtenerSingleton() {
        return ResponseEntity.ok(new EstadoResponse("Singleton", singletonService.obtenerValor()));
    }

    @Override
    public ResponseEntity<EstadoResponse> reiniciarSingleton() {
        singletonService.reiniciar();
        return ResponseEntity.ok(new EstadoResponse("Singleton", singletonService.obtenerValor()));
    }

    @Override
    public ResponseEntity<EstadoResponse> actualizarManual(Integer valor) {
        EstadoManual manual = new EstadoManual();
        manual.actualizarValor(valor);
        return ResponseEntity.ok(new EstadoResponse("Manual", manual.obtenerValor()));
    }

    @Override
    public ResponseEntity<EstadoResponse> obtenerManual() {
        EstadoManual manual = new EstadoManual();
        return ResponseEntity.ok(new EstadoResponse("Manual", manual.obtenerValor()));
    }
}
