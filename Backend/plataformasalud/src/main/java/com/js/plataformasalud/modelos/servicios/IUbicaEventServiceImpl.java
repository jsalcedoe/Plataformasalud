package com.js.plataformasalud.modelos.servicios;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IubicacionEventodao;
import com.js.plataformasalud.modelos.entidades.UbicacionEvent;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class IUbicaEventServiceImpl implements IUbicaEventService{
	
	private IubicacionEventodao iubicaeventdao;

	@Override
	@Transactional(readOnly = true)
	public List<UbicacionEvent> findAll() {
		
		return (List<UbicacionEvent>) iubicaeventdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public UbicacionEvent findById(Long idubieventpac) {
		
		return iubicaeventdao.findById(idubieventpac).orElse(null);
	}

	@Override
	@Transactional
	public UbicacionEvent save(UbicacionEvent ubicaevent) {
		
		return iubicaeventdao.save(ubicaevent);
	}

	@Override
	@Transactional
	public void delete(Long idubieventpac) {
		iubicaeventdao.deleteById(idubieventpac);
		
	}

}
