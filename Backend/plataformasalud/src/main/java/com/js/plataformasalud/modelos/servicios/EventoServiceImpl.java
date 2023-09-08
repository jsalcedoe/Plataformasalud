package com.js.plataformasalud.modelos.servicios;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.Ieventosdao;
import com.js.plataformasalud.modelos.entidades.Evento;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EventoServiceImpl implements iEventosService {
	
	
	private Ieventosdao eventodao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Evento> findAll() {
		
		return (List<Evento>) eventodao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Evento findById(Long ideventpac) {
		
		return eventodao.findById(ideventpac).orElse(null);
	}

	@Override
	@Transactional
	public Evento save(Evento evento) {
		
		return eventodao.save(evento);
	}

	@Override
	@Transactional(readOnly = true)
	public void delete(Long ideventpac) {
		eventodao.deleteById(ideventpac);
		
	}


}
