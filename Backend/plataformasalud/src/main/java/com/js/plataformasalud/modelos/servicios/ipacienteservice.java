package com.js.plataformasalud.modelos.servicios;

import java.util.List;


import com.js.plataformasalud.modelos.entidades.Paciente;

public interface ipacienteservice {
	
	// en esta interfaz se suscriben los contratos de servicios como listar, grabar, editar, borrar.
	public List<Paciente> findAll();
	
	public Paciente findById(Long idpac);
	
	public Paciente save (Paciente paciente);
	
	public List<Paciente> buscarPacientesPorNombreODocumento(String keyword);
	

	
}
