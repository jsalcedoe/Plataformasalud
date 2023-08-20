package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.OrdMedIns;

public interface IOrdMedInsService {
	
	public List<OrdMedIns> findAll();
	
	public OrdMedIns findById(Long idordmedins);
	
	public OrdMedIns save (OrdMedIns ordenmed);
	
	public void delete (Long idordmedins);

}
