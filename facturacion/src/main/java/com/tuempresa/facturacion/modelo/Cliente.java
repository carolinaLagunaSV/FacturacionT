package com.tuempresa.facturacion.modelo;


import javax.persistence.*;

import org.openxava.annotations.*;

import lombok.*;

@Entity
@Getter @Setter
@View(name="Simple",members="numero, nombre" )

public class Cliente {
	
	
	
	
	@Column(length=50)
	@Required
	String nombre;
	
	@Embedded 
	 Direccion direccion; 
	

}
