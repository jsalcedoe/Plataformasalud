package com.js.plataformasalud.modelos.servicios;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IDiagnosticoDescripcionQxDao;
import com.js.plataformasalud.modelos.entidades.DiagnosticoDescripcionQx;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class IDiagnostivoDescripcionQxServiceImpl implements IDiagnosticoDescripcionQxService {
	
	private IDiagnosticoDescripcionQxDao dxatedao;

	@Override
	@Transactional(readOnly = true)
	public List<DiagnosticoDescripcionQx> findAll() {
		
		return (List<DiagnosticoDescripcionQx>) dxatedao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public DiagnosticoDescripcionQx findById(Long iddxathcpac) {
		
		return dxatedao.findById(iddxathcpac).orElse(null);
	}

	@Override
	@Transactional
	public DiagnosticoDescripcionQx save(DiagnosticoDescripcionQx dxqxpac) {
		
		return dxatedao.save(dxqxpac);
	}

	

	

}
