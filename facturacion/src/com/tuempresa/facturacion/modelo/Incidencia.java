package com.tuempresa.facturacion.modelo;

import lombok.*;

@Getter @Setter
public class Incidencia {

	@Getter @Setter
	int cantidad; 
	
	@Getter @Setter
	int precio;
	
	public int getImporte() {
		return cantidad*precio;
	}
}
//leccion 9
/*int numero;
String descripcion;

public String getDescripcion() {
	if (descripcion==null)return "Todavía no hay descripción";
	return descripcion;
}
 */