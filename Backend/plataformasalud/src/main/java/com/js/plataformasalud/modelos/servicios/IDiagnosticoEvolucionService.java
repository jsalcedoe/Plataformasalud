package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.DiagnosticoEvolucion;

public interface IDiagnosticoEvolucionService {
	
	public List<DiagnosticoEvolucion> findAll();
	
	public DiagnosticoEvolucion findById(Long iddxevopac);
	
	public DiagnosticoEvolucion save (DiagnosticoEvolucion dxevopac);

}
