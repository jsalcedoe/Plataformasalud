package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IDiagnosticoHistoriaClinicaDao;
import com.js.plataformasalud.modelos.entidades.DiagnosticoHistoriaClinica;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class IDiagnosticoHistoriaClinicaServiceImpl implements IDiagnosticoHistoriaClinicaServices {
	
	private IDiagnosticoHistoriaClinicaDao dxhcpacdao;
	
	@Override
	@Transactional(readOnly = true)
	public List<DiagnosticoHistoriaClinica> findAll() {
		
		return (List<DiagnosticoHistoriaClinica>) dxhcpacdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public DiagnosticoHistoriaClinica findById(Long iddxhcpac) {
		
		return dxhcpacdao.findById(iddxhcpac).orElse(null);
	}

	@Override
	@Transactional
	public DiagnosticoHistoriaClinica save(DiagnosticoHistoriaClinica dxhcpac) {
		
		return dxhcpacdao.save(dxhcpac);
	}

}
