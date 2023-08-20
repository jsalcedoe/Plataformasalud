package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.Entidad;

public interface IEntidadService {
	
	public List<Entidad> findAll();
	
	public Entidad findById(Long ideapb);
	
	public Entidad save(Entidad eapb);
	
	public void delete(Long ideapb);

}
