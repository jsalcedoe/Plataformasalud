package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.ITipoPacienteDao;
import com.js.plataformasalud.modelos.entidades.TipoPaciente;

@Service

public class ITipoPacienteServiceImpl implements ITipoPacienteService {
	
	private ITipoPacienteDao tipacdao;
	
	

	public ITipoPacienteServiceImpl(ITipoPacienteDao tipacdao) {
		super();
		this.tipacdao = tipacdao;
	}

	@Override
	@Transactional(readOnly = true)
	public List<TipoPaciente> findAll() {
		
		return (List<TipoPaciente>) tipacdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public TipoPaciente findById(Long idtipac) {
		
		return tipacdao.findById(idtipac).orElse(null);
	}

	@Override
	@Transactional
	public TipoPaciente save(TipoPaciente tipac) {
		
		return tipacdao.save(tipac);
	}

	@Override
	@Transactional
	public void delete(Long idtipac) {
		
		tipacdao.deleteById(idtipac);
		
	}

}
