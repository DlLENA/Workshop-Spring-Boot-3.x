package com.ejemplo.demo.api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record PrestamoRequest (
	@NotNull(message = "El monto no puede ser nulo")
	@Positive(message = "El monto debe ser mayor a 0")
	BigDecimal monto,
	
	@NotNull(message = "La tasa anual no puede ser nula")
	@Positive(message = "La tasa anual debe sr mayor a 0")
	BigDecimal tasaAnual,
	
	@Min(value = 1, message = "El plazo minimo es 1 mes")
	@Max(value = 360, message = "El plazo maximo son 360 meses (30 años)")
	int meses

){
}
