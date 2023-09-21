package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.CitaPaciente;

public interface ICitaPacienteService {
public List<CitaPaciente> findAll();
	
	public CitaPaciente findById(Long idcitmed);
	
	public CitaPaciente save (CitaPaciente citpacv);
	
	public void delete (Long idcitmed);
}
