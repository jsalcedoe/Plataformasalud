package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.TipoDiagnostico;

public interface ITipoDiagnosticoService {
	
	public List <TipoDiagnostico> findAll();
	
	public TipoDiagnostico findById (Long idtypdx);
	
	public TipoDiagnostico save (TipoDiagnostico typdx);
	
	public List <TipoDiagnostico> findByDetypdx (String term);

}
