package com.tuempresa.facturacion.calculadores;

import org.openxava.calculators.*;

import com.tuempresa.facturcion.util.*;

public class CalculadorPorcentajeIVA implements ICalculator{
	@Override
	public Object calculate() throws Exception {
	return PreferenciasFacturacion.getPorcentajeIVADefecto();	
  
	}

}
