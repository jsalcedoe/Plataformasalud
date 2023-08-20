package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.GrupoMedIns;

public interface IGrupoMedInsService {
	
	public List<GrupoMedIns> findAll();
	
	public GrupoMedIns findById(Long idgrupmedins);
	
	public GrupoMedIns save (GrupoMedIns grupmedins);
	
	public void delete(Long idgrupmedins);

}
