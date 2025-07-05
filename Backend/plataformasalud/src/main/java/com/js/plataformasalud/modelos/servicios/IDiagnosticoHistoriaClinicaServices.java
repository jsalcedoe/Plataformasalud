package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.DiagnosticoHistoriaClinica;

public interface IDiagnosticoHistoriaClinicaServices {
	
	public List<DiagnosticoHistoriaClinica> findAll();
	
	public DiagnosticoHistoriaClinica findById(Long iddxhcpac);
	
	public DiagnosticoHistoriaClinica save (DiagnosticoHistoriaClinica dxhcpac);

}
