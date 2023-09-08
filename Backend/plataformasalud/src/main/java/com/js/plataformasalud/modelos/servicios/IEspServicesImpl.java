package com.js.plataformasalud.modelos.servicios;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IEspecialidadesDao;
import com.js.plataformasalud.modelos.entidades.Especialidades;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class IEspServicesImpl implements IEspServices {
	
	private IEspecialidadesDao espdao;

	@Override
	@Transactional(readOnly = true)
	public List<Especialidades> findAll() {
		
		return (List<Especialidades>)espdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Especialidades findById(Long idepecialidades) {
		
		return espdao.findById(idepecialidades).orElse(null);
	}

	@Override
	@Transactional
	public Especialidades save(Especialidades especialidades) {
		
		return espdao.save(especialidades);
	}

	@Override
	@Transactional
	public void delete(Long idespecialidades) {
		espdao.deleteById(idespecialidades);
		
	}

}
