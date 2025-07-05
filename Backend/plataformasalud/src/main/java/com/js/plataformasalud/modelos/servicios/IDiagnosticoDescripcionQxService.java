package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.DiagnosticoDescripcionQx;

public interface IDiagnosticoDescripcionQxService {
	
	public List<DiagnosticoDescripcionQx> findAll();
	
	public DiagnosticoDescripcionQx findById(Long iddxqxpac);
	
	public DiagnosticoDescripcionQx save (DiagnosticoDescripcionQx dxqxpac);
	
	

}
