package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.PermisoporRol;

public interface IPermisoporRolService {
	
	public List <PermisoporRol> findAll();
	
	public PermisoporRol findById(Long idpermrol);
	
	public PermisoporRol save (PermisoporRol permrol);
	

}
