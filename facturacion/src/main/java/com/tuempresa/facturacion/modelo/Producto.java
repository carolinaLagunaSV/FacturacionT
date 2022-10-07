package com.tuempresa.facturacion.modelo;

import java.math.*;

import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;


@Entity @Getter @Setter
public class Producto {
	@Id @Column(length=9) 
	 int numero;
	
	@Column(length=50) @Required
	 String descripcion;
	
	
	@ManyToOne( 
			fetch=FetchType.LAZY, 
			 optional=true) 
			 @DescriptionsList 
			 Categoria categoria; 
	
	@Money  
	BigDecimal precio;
	
	@Files // Una galería de fotos completa está disponible
	@Column(length=32) // La cadena de 32 de longitud es para almacenar la clave de la galería
	String fotos;
	@TextArea // Esto es para un texto grande, se usará un área de texto o equivalente
	String observaciones;

	

}
