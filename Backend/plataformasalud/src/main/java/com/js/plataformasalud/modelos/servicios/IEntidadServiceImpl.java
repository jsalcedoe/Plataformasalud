package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IEntidadDao;
import com.js.plataformasalud.modelos.entidades.Entidad;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class IEntidadServiceImpl implements IEntidadService {
	
	private IEntidadDao entdao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Entidad> findAll() {
		
		return (List<Entidad>) entdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Entidad findById(Long ideapb) {
		
		return entdao.findById(ideapb).orElse(null);
	}

	@Override
	@Transactional
	public Entidad save(Entidad eapb) {
		
		return entdao.save(eapb);
	}

}
