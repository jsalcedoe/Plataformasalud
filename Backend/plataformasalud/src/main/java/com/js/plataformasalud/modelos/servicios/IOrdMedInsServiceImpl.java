package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IOrdMedInsDao;
import com.js.plataformasalud.modelos.entidades.OrdMedIns;

@Service

public class IOrdMedInsServiceImpl implements IOrdMedInsService {
	
	@Autowired
	private IOrdMedInsDao orden;

	@Override
	@Transactional(readOnly = true)
	public List<OrdMedIns> findAll() {
		
		return (List<OrdMedIns>)orden.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public OrdMedIns findById(Long idordmedins) {
		
		return orden.findById(idordmedins).orElse(null);
	}

	@Override
	@Transactional
	public OrdMedIns save(OrdMedIns ordenmed) {
		
		return orden.save(ordenmed);
	}

	@Override
	@Transactional
	public void delete(Long idordmedins) {
	
		orden.deleteById(idordmedins);
	}

}
