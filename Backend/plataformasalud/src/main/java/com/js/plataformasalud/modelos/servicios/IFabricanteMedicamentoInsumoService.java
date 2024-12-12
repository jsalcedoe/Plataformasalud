package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.FabricanteMedicamentoInsumo;

public interface IFabricanteMedicamentoInsumoService {
	
	public List <FabricanteMedicamentoInsumo> findAll();
	
	public FabricanteMedicamentoInsumo findById(Long idfabmedins);
	
	public FabricanteMedicamentoInsumo save (FabricanteMedicamentoInsumo fabmedins);

}
