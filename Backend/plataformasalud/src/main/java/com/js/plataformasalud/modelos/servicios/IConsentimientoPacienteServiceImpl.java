package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IConsentimientoPacienteDao;
import com.js.plataformasalud.modelos.entidades.ConsentimientoPaciente;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class IConsentimientoPacienteServiceImpl implements IConsentimientoPacienteService {
	
	private IConsentimientoPacienteDao consinfpacdao;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<ConsentimientoPaciente> findAll() {
		
		return (List<ConsentimientoPaciente>) consinfpacdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public ConsentimientoPaciente findById(Long idconsinfpac) {
		
		return consinfpacdao.findById(idconsinfpac).orElse(null);
	}

	@Override
	@Transactional
	public ConsentimientoPaciente save(ConsentimientoPaciente consinfpac) {
		
		return consinfpacdao.save(consinfpac);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ConsentimientoPaciente> findByEventconsinfpac_fk(Long idevent) {
		
		return (List<ConsentimientoPaciente>)consinfpacdao.findByEventconsinfpac_fk(idevent);
	}
	
	

}
