package com.tuempresa.facturacion.modelo;

import java.math.*;
import java.time.*;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;
import org.openxava.jpa.*;

import com.tuempresa.facturacion.calculadores.*;

import lombok.*;

@Entity @Getter @Setter
@View(members = 
        "anyo, numero, fecha,"+
     "datos {"+    
		"cliente;"+
        "detalles;"+
		"observaciones"+
     "}"
 )
abstract public class DocumentoComercial extends Identificable{
	
	@DefaultValueCalculator(CurrentYearCalculator.class)
	@Column(length = 4)
	int anyo;
	
	@Column(length = 6)
	
	@ReadOnly 
	int numero;

	
	@Required
	@DefaultValueCalculator(CurrentLocalDateCalculator.class)
	LocalDate fecha;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@ReferenceView("simple")
	Cliente cliente;
	  
	@ElementCollection
	@ListProperties("producto.numero, producto.descripcion, cantidad, precioPorUnidad,"+
		 " importe+["
		 + "documentoComercial.porcentajeIVA,"
		 + "documentoComercial.iva,"
		 + "documentoComercial.importeTotal"
		 + "]"
		 )
	 Collection<Detalle>detalles;
	
	@Stereotype("MEMO")  
	String observaciones;
	
	@DefaultValueCalculator(CalculadorPorcentajeIVA.class)
	@Digits(integer = 2,fraction = 0)
	BigDecimal porcentajeIVA;
	
	@ReadOnly
	@Stereotype("DINERO")
	@Calculation("sum(detalles.importe) * porcentajeIVA / 100")
	BigDecimal iva;
	
	@ReadOnly
	@Stereotype("DINERO")
	@Calculation("sum(detalles.importe)+iva")
	BigDecimal importeTotal;
	
	@PrePersist 
	private void calcularnumero() {
		
		Query query = XPersistence.getManager().createQuery(
				"select max(f.numero) from " +
				getClass().getSimpleName() + 
				" f where f.anyo = :anyo");
				 query.setParameter("anyo", anyo);
				 Integer ultimoNumero = (Integer) query.getSingleResult();
				 this.numero = ultimoNumero == null ? 1 : ultimoNumero + 1;	
		
		
	}
	
}
