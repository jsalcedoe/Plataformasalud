package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.ICiudadDao;
import com.js.plataformasalud.modelos.entidades.Ciudad;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ICiudadServiceImpl implements ICiudadService {
	
	private ICiudadDao ciudao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Ciudad> findAll() {
		
		return (List<Ciudad>)ciudao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Ciudad FindById(Long codciudad) {
		
		return ciudao.findById(codciudad).orElse(null);
	}

	@Override
	@Transactional
	public Ciudad save(Ciudad ciudad) {
		
		return ciudao.save(ciudad);
	}

	@Override
	@Transactional
	public void delete(Long codciudad) {
		ciudao.deleteById(codciudad);
		
	}

}
