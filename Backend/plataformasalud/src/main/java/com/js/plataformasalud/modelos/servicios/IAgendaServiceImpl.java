package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IAgendaDao;
import com.js.plataformasalud.modelos.entidades.Agenda;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class IAgendaServiceImpl implements IAgendaService {
	
	private IAgendaDao agmeddao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Agenda> findAll() {
		
		return (List<Agenda>) agmeddao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Agenda findById(Long idagmed) {
		
		return agmeddao.findById(idagmed).orElse(null);
	}

	@Override
	@Transactional
	public Agenda save(Agenda agmed) {
		
		return agmeddao.save(agmed);
	}
	
	

}
