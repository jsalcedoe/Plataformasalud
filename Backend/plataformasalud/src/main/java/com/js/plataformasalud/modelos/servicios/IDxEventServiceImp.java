package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IDxEventDao;
import com.js.plataformasalud.modelos.entidades.DiagnosticoEvent;


@Service
public class IDxEventServiceImp implements IDxEventService {
	
	@Autowired
	private IDxEventDao dxeventdao;

	@Override
	@Transactional(readOnly = true)
	public List<DiagnosticoEvent> findAll() {
		
		return (List<DiagnosticoEvent>) dxeventdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public DiagnosticoEvent findById(Long iddxpac) {
		
		return dxeventdao.findById(iddxpac).orElse(null);
	}

	@Override
	@Transactional
	public DiagnosticoEvent save(DiagnosticoEvent dxevent) {
		
		return dxeventdao.save(dxevent);
	}

	@Override
	@Transactional
	public void delete(Long iddxpac) {
		
		dxeventdao.deleteById(iddxpac);
	}

}
