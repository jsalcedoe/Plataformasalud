package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.ICitaPacienteDao;
import com.js.plataformasalud.modelos.entidades.CitaPaciente;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class ICitaPacienteServiceImpl implements ICitaPacienteService {
	
	private ICitaPacienteDao citmedao;
	
	@Override
	@Transactional(readOnly = true)
	public List<CitaPaciente> findAll() {
		
		return citmedao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public CitaPaciente findById(Long idcitmed) {
		
		return citmedao.findById(idcitmed).orElse(null);
	}

	@Override
	@Transactional
	public CitaPaciente save(CitaPaciente citpac) {
		
		return citmedao.save(citpac);
	}

	@Override
	public void delete(Long idcitmed) {
		citmedao.deleteById(idcitmed);
	}

	

}
