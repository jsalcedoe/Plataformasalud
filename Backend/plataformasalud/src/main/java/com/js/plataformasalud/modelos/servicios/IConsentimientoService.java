package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.Consentimiento;

public interface IConsentimientoService {
	
	public List<Consentimiento> findAll();
	
	public Consentimiento findById(Long idconsinf);
	
	public Consentimiento save (Consentimiento coninf);

}
