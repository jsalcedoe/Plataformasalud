package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.Estado;

public interface IEstadoService {
	
	public List<Estado> findAll();
	
	public Estado findById(Long idstatus);
	
	public Estado save (Estado estado);
	
}
