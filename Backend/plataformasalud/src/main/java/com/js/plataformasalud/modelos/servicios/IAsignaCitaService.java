package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.AsignaCita;

public interface IAsignaCitaService {
	
	public List<AsignaCita> findAll();
	
	public AsignaCita findById (Long idascit);
	
	public AsignaCita save (AsignaCita ascit);
	
	

}
