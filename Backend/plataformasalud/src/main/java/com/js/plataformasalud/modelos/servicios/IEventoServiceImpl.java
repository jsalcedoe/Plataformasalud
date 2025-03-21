package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IEventoDao;
import com.js.plataformasalud.modelos.entidades.Evento;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class IEventoServiceImpl implements IEventoService {
	
	private IEventoDao eventdao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Evento> findAll() {
		
		return (List<Evento>) eventdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Evento findById(Long idevent) {
		
		return eventdao.findById(idevent).orElse(null);
	}

	@Override
	@Transactional
	public Evento save(Evento event) {
		
		return eventdao.save(event);
	}

}
