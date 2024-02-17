package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.Ubicacion;

public interface IUbicaService {
	
	public List<Ubicacion> findAll();
	
	public Ubicacion findById(Long idubica);
	
	public Ubicacion save (Ubicacion ubica);

}
