package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.DiagnosticoAtencion;

public interface IDiagnosticoAtencionService {
	
	public List<DiagnosticoAtencion> findAll();
	
	public DiagnosticoAtencion findById(Long iddxathcpac);
	
	public DiagnosticoAtencion save (DiagnosticoAtencion dxatehcpac);

}
