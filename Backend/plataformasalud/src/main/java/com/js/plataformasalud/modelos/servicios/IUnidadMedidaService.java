package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.UnidadMedidaMedIns;

public interface IUnidadMedidaService {
	
	public List<UnidadMedidaMedIns> findAll();
	
	public UnidadMedidaMedIns findById(Long idmed);
	
	public UnidadMedidaMedIns save(UnidadMedidaMedIns unimed);
	
	public void delete (Long idmed);

}
