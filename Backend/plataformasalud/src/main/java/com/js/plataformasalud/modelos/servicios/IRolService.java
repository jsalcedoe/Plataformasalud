package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.Rol;

public interface IRolService {
	
	public List<Rol> findAll();
	
	public Rol findById(Long idrol);
	
	public Rol save (Rol rol);
	

}
