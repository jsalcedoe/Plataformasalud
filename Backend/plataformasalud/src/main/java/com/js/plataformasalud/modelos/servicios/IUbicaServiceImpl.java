package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IUbicacionDao;
import com.js.plataformasalud.modelos.entidades.Ubicacion;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IUbicaServiceImpl implements IUbicaService {
	
	
	private IUbicacionDao ubicadao;

	@Override
	@Transactional(readOnly = true)
	public List<Ubicacion> findAll() {
		
		return (List<Ubicacion>) ubicadao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Ubicacion findById(Long idubica) {
		
		return ubicadao.findById(idubica).orElse(null);
	}

	@Override
	@Transactional
	public Ubicacion save(Ubicacion ubica) {
		
		return ubicadao.save(ubica);
	}

	

}
