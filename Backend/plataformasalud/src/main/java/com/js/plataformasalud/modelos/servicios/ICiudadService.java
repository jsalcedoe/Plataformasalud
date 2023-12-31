package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.Ciudad;

public interface ICiudadService {
	
	public List<Ciudad> findAll();
	
	public Ciudad FindById(Long codciudad);
	
	public Ciudad save (Ciudad ciudad);
	
	public void delete (Long codciudad);

}
