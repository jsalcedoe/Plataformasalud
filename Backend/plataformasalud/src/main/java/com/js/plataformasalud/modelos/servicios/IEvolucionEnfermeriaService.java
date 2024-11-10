package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.EvolucionEnfermeria;

public interface IEvolucionEnfermeriaService {
	
	public List<EvolucionEnfermeria> findAll();
	
	public EvolucionEnfermeria FindById(Long idevoenf);
	
	public EvolucionEnfermeria save (EvolucionEnfermeria evoenf);

}
