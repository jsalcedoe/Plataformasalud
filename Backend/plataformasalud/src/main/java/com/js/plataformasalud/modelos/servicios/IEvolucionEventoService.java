package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.EvolucionEvento;

public interface IEvolucionEventoService {
	
	public List <EvolucionEvento> findAll();
	
	public EvolucionEvento findById(Long idevol);
	
	public EvolucionEvento save (EvolucionEvento evol);
	
	public List <EvolucionEvento> findByEventevo_Fk(Long idevent);
	
	public boolean existsById (Long idevol);
	
	

}
