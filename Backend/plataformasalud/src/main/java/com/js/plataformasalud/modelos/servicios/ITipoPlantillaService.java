package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.TipoPlantilla;

public interface ITipoPlantillaService {
	
	public List<TipoPlantilla> findAll();
	
	public TipoPlantilla findById (Long idtytemp);
	
	public TipoPlantilla save (TipoPlantilla tytemp);

}
