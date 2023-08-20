package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.Evento;


public interface iEventosService {

	public List<Evento> findAll();
	
	public Evento findById(Long ideventpac);
	
	public Evento save (Evento evento);
	
	public void delete (Long ideventpac); 
	

}
