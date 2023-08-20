package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.TipoPaciente;

public interface ITipoPacienteService {
	public List<TipoPaciente> findAll();
	
	public TipoPaciente findById(Long idtipac);
	
	public TipoPaciente save (TipoPaciente tipac);
	
	public void delete (Long idtipac);

}
