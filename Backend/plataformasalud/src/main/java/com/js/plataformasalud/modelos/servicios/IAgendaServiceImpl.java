package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IAgendaDao;
import com.js.plataformasalud.modelos.entidades.Agenda;

@Service
public class IAgendaServiceImpl implements IAgendaService {
	
	private IAgendaDao agmedao;
	
	
	public IAgendaServiceImpl(IAgendaDao agmedao) {
		super();
		this.agmedao = agmedao;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Agenda> findAll() {
		
		return (List<Agenda>) agmedao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Agenda findById(Long idagmed) {
		
		return agmedao.findById(idagmed).orElse(null);
	}

	@Override
	@Transactional
	public Agenda save(Agenda agmed) {
		
		return agmedao.save(agmed);
	}

	@Override
	@Transactional
	public void delete(Long idagmed) {
		
		agmedao.deleteById(idagmed);
	}

}