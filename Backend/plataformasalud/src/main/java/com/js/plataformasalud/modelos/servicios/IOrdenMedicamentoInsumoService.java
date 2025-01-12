package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.OrdenMedicamentoInsumo;

public interface IOrdenMedicamentoInsumoService {
	
	public List<OrdenMedicamentoInsumo> findAll();
	
	public OrdenMedicamentoInsumo findById(Long idordmedins);
	
	public OrdenMedicamentoInsumo save (OrdenMedicamentoInsumo ordmedins);

}
