package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.EquipoQx;

public interface IEquipoQxService {
	
	public List<EquipoQx> findAll();
	
	public EquipoQx findById(Long ideqqx);
	
	public EquipoQx save (EquipoQx eqqx);

}
