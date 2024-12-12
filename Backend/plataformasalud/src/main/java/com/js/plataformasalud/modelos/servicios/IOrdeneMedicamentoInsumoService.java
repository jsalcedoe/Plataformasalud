package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.OrdenesMedicamentosInsumos;

public interface IOrdeneMedicamentoInsumoService {
	
	public List<OrdenesMedicamentosInsumos> findAll();
	
	public OrdenesMedicamentosInsumos findById (Long idordmedins);
	
	public OrdenesMedicamentosInsumos save (OrdenesMedicamentosInsumos ordmedins);
	
	

}
