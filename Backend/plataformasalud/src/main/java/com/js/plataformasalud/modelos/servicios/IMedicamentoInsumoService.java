package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.MedicamentoInsumo;

public interface IMedicamentoInsumoService {
	
	public List<MedicamentoInsumo> findAll();
	
	public MedicamentoInsumo findById(Long idmedins);
	
	public MedicamentoInsumo save (MedicamentoInsumo medins);
	
	public void delete (Long idmedins);

}
