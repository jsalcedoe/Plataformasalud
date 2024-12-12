package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.Agenda;

public interface IAgendaService {
	
	public List<Agenda> findAll();
	
	public Agenda findById (Long idagmed);
	
	public Agenda save (Agenda agmed);
	
	

}
