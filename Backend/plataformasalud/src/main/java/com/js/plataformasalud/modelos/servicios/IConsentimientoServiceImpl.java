package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IConsentimientoDao;
import com.js.plataformasalud.modelos.entidades.Consentimiento;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class IConsentimientoServiceImpl implements IConsentimientoService {
	
	private IConsentimientoDao coninfdao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Consentimiento> findAll() {
		
		return (List<Consentimiento>) coninfdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Consentimiento findById(Long idconsinf) {
		
		return coninfdao.findById(idconsinf).orElse(null);
	}

	@Override
	@Transactional
	public Consentimiento save(Consentimiento coninf) {
		
		return coninfdao.save(coninf);
	}

}
