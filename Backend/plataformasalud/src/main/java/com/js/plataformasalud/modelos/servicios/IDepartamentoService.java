package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.Departamento;

public interface IDepartamentoService {
	
	public List<Departamento> findAll();
	
	public Departamento findById(Long coddep);
	
	public Departamento save (Departamento departamento);
	
	public void delete (Long coddep);
}
