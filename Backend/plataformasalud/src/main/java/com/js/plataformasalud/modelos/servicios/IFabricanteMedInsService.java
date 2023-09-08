package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.FabricanteMedIns;

public interface IFabricanteMedInsService {
	
	public List<FabricanteMedIns> findAll();
	
	public FabricanteMedIns findById(Long nitfabmedins);
	
	public FabricanteMedIns save (FabricanteMedIns fabmedins);
	
	public void delete (Long nitfabmedins);

}
