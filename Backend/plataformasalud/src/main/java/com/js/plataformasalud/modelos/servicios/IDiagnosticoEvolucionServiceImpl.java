package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IDiagnosticoEvolucionDao;
import com.js.plataformasalud.modelos.entidades.DiagnosticoEvolucion;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class IDiagnosticoEvolucionServiceImpl implements IDiagnosticoEvolucionService{
	
	private IDiagnosticoEvolucionDao dxevodao;
	
	@Override
	@Transactional(readOnly = true)
	public List<DiagnosticoEvolucion> findAll() {
		
		return (List<DiagnosticoEvolucion>) dxevodao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public DiagnosticoEvolucion findById(Long iddxevopac) {
		
		return dxevodao.findById(iddxevopac).orElse(null);
	}

	@Override
	@Transactional
	public DiagnosticoEvolucion save(DiagnosticoEvolucion dxevopac) {
		
		return dxevodao.save(dxevopac);
	}

}
