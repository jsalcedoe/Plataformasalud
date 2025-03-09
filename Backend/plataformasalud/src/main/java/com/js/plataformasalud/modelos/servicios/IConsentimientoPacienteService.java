package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.ConsentimientoPaciente;

public interface IConsentimientoPacienteService {
	
	public List<ConsentimientoPaciente> findAll();
	
	public ConsentimientoPaciente findById(Long idconsinfpac);
	
	public ConsentimientoPaciente save(ConsentimientoPaciente consinfpac);
	
	public List<ConsentimientoPaciente> findByEventconsinfpac_fk(Long idevent);

}
