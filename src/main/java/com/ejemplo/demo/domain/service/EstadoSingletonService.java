package com.ejemplo.demo.domain.service;

import org.springframework.stereotype.Service;

@Service
public class EstadoSingletonService {
    private int valorActual = 0;

    public void actualizarValor(int valor) {
        this.valorActual = valor;
    }

    public int obtenerValor() {
        return this.valorActual;
    }

    public void reiniciar() {
        this.valorActual = 0;
    }
}