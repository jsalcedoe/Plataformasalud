package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.PresentacionMedicamentoInsumo;

public interface IPresentacionMedicamentoInsumoService {
	
	public List<PresentacionMedicamentoInsumo> findAll();
	
	public PresentacionMedicamentoInsumo FindById(Long idpmedins);
	
	public PresentacionMedicamentoInsumo save(PresentacionMedicamentoInsumo pmedins);
	
	public List<PresentacionMedicamentoInsumo> findByPmedins(String detpmedins);

}
