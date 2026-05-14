package com.ejemplo.demo.domain.service;

public class EstadoManual {

	private int valorActual = 0;

    public void actualizarValor(int valor) {
        this.valorActual = valor;
    }

    public int obtenerValor() {
        return this.valorActual;
    }
}
