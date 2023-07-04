package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IUbicacionDao;
import com.js.plataformasalud.modelos.entidades.Ubicacion;

@Service
public class IUbicaServiceImpl implements IUbicaService {
	
	@Autowired
	private IUbicacionDao ubicadao;

	@Override
	@Transactional(readOnly = true)
	public List<Ubicacion> findAll() {
		
		return (List<Ubicacion>) ubicadao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Ubicacion findById(Long idubicaciones) {
		
		return ubicadao.findById(idubicaciones).orElse(null);
	}

	@Override
	@Transactional
	public Ubicacion save(Ubicacion ubica) {
		
		return ubicadao.save(ubica);
	}

	@Override
	@Transactional
	public void delete(Long idubicaciones) {
	
		ubicadao.deleteById(idubicaciones);
	}

}
