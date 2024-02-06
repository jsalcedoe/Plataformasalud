package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IEvolucionEventoDao;
import com.js.plataformasalud.modelos.entidades.EvolucionEvento;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class IEvolucionEventoServiceImpl implements IEvolucionEventoService {
	
	private IEvolucionEventoDao evodao;
	
	@Override
	@Transactional(readOnly = true)
	public List<EvolucionEvento> findAll() {
		
		return (List<EvolucionEvento>) evodao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public EvolucionEvento findById(Long idevol) {
		
		return evodao.findById(idevol).orElse(null);
	}

	@Override
	@Transactional
	public EvolucionEvento save(EvolucionEvento evol) {
		
		return evodao.save(evol);
	}

}
