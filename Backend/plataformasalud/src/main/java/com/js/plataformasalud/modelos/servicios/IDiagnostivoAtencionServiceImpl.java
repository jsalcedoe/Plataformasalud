package com.js.plataformasalud.modelos.servicios;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IDiagnosticoAtencionDao;
import com.js.plataformasalud.modelos.entidades.DiagnosticoAtencion;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class IDiagnostivoAtencionServiceImpl implements IDiagnosticoAtencionService {
	
	private IDiagnosticoAtencionDao dxatedao;

	@Override
	@Transactional(readOnly = true)
	public List<DiagnosticoAtencion> findAll() {
		
		return (List<DiagnosticoAtencion>) dxatedao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public DiagnosticoAtencion findById(Long iddxathcpac) {
		
		return dxatedao.findById(iddxathcpac).orElse(null);
	}

	@Override
	@Transactional
	public DiagnosticoAtencion save(DiagnosticoAtencion dxatehcpac) {
		
		return dxatedao.save(dxatehcpac);
	}

	

	

}
