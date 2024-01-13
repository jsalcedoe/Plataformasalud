package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.Permiso;

public interface IPermisoServices {
	
	public List<Permiso> findAll();
	
	public Permiso findById (Long idperm);
	
	public Permiso save (Permiso permiso);

}
