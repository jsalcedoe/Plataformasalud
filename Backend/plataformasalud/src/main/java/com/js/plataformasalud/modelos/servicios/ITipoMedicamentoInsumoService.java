package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.TipoMedicamentoInsumo;

public interface ITipoMedicamentoInsumoService {
	
	public List<TipoMedicamentoInsumo> findAll();
	
	public TipoMedicamentoInsumo FindById(Long idtmedins);
	
	public TipoMedicamentoInsumo save(TipoMedicamentoInsumo tmedins);

}
