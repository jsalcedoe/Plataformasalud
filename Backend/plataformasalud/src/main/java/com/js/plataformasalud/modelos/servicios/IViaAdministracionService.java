package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.ViaAdministracion;

public interface IViaAdministracionService {
	
	public List<ViaAdministracion> findAll();
	
	public ViaAdministracion findById (Long idviadm);
	
	public ViaAdministracion save (ViaAdministracion viadm);
	
	

}
