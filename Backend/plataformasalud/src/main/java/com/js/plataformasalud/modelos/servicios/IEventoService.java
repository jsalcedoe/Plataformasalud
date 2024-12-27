package com.js.plataformasalud.modelos.servicios;

import java.util.List;
 

import com.js.plataformasalud.modelos.entidades.Evento;


public interface IEventoService {
	
	public List<Evento> findAll();
	
	public Evento findById(Long idevent);
	
	public Evento save (Evento event);
	
}
