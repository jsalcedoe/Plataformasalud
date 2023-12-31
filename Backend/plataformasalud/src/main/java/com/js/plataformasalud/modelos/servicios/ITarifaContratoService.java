package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.TarifaContrato;

public interface ITarifaContratoService {
	
	public List<TarifaContrato> findAll();
	
	public TarifaContrato findById(Long idtarifcont);
	
	public TarifaContrato save (TarifaContrato tarifcont);
	
	public void delete (Long idtarifcont);

}
