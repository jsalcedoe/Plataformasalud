package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.Plantillas;

public interface IPlantillasService {
	
	public List<Plantillas> findAll();
	
	public Plantillas findById(Long idtemp);
	
	public Plantillas save (Plantillas temp);

}
