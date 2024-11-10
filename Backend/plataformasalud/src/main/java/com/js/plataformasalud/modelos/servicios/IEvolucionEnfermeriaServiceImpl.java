package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IEvolucionEnfermeriaDao;
import com.js.plataformasalud.modelos.entidades.EvolucionEnfermeria;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class IEvolucionEnfermeriaServiceImpl implements IEvolucionEnfermeriaService {
	
	private IEvolucionEnfermeriaDao evoenfdao;
	
	@Override
	@Transactional(readOnly = true)
	public List<EvolucionEnfermeria> findAll() {
		
		return (List<EvolucionEnfermeria>) evoenfdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public EvolucionEnfermeria FindById(Long idevoenf) {
		
		return evoenfdao.findById(idevoenf).orElse(null);
	}

	@Override
	public EvolucionEnfermeria save(EvolucionEnfermeria evoenf) {
		
		return evoenfdao.save(evoenf) ;
	}

}
