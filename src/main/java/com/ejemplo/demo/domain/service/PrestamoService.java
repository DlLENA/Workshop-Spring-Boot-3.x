package com.ejemplo.demo.domain.service;

import com.ejemplo.demo.api.dto.PrestamoResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class PrestamoService {
	
	public PrestamoResponse simularPrestamo(BigDecimal monto, BigDecimal tasaAnual, int meses) {
		if (monto.compareTo(BigDecimal.ZERO) <= 0 || tasaAnual.compareTo(BigDecimal.ZERO) <= 0 || meses <=0) {
			
			throw new IllegalArgumentException("Los valores deben ser mayores a cero. ");
		}
		
		//Preparamos las variables para la formula 
		//r = tasa mensual = (tasaAnual / 100 / 12)
		BigDecimal tasaMensual = tasaAnual.divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP)
				                          .divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP);
		
		//aplicamosla formula: cuota = P * (r * (1 + r)^n) / ((1 + r)^n - 1)
		
		//paso 1: (1 + r)^n
		BigDecimal unoMasR = BigDecimal.ONE.add(tasaMensual);
		BigDecimal potencia = unoMasR.pow(meses);
		
		//paso 2: r * (1 + r)^n
		BigDecimal numerador = tasaMensual.multiply(potencia);
		
		//paso 3: ((1 + r)^n - 1)
		BigDecimal denominador = potencia.subtract(BigDecimal.ONE);
		
		//calcular los resultados finales
		BigDecimal cuotaMensual;
		
		//validadomos por si la tasa fuera 0% para asi evitar dividir por cero 
		if (tasaAnual.compareTo(BigDecimal.ZERO) == 0) {
			cuotaMensual = monto.divide(BigDecimal.valueOf(meses), 2, RoundingMode.HALF_UP);
		}else {
			cuotaMensual = monto.multiply(numerador.divide(denominador, 10, RoundingMode.HALF_UP))
                                .setScale(2, RoundingMode.HALF_UP); //lo redondeamos a 2 decimales     
		}
		
		BigDecimal totalPagar = cuotaMensual.multiply(BigDecimal.valueOf(meses));
		BigDecimal interesTotal = totalPagar.subtract(monto);
		
		return new PrestamoResponse(cuotaMensual, interesTotal, totalPagar);
		
	}

}
