package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.ITipoDiagnosticoDao;
import com.js.plataformasalud.modelos.entidades.TipoDiagnostico;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class ITipoDiagnosticoServiceImpl implements ITipoDiagnosticoService {
	
	private ITipoDiagnosticoDao typdxdao;
	
	@Override
	@Transactional(readOnly = true)
	public List<TipoDiagnostico> findAll() {
		
		return (List<TipoDiagnostico>)typdxdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public TipoDiagnostico findById(Long idtypdx) {
		
		return typdxdao.findById(idtypdx).orElse(null);
	}

	@Override
	@Transactional
	public TipoDiagnostico save(TipoDiagnostico typdx) {
		
		return typdxdao.save(typdx);
	}

}
