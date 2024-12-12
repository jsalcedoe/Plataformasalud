package com.js.plataformasalud.modelos.servicios;

import java.util.List;


import com.js.plataformasalud.modelos.entidades.UnidadMedida;

public interface IUnidadMedidaService {
	
	public List<UnidadMedida> findAll();
	
	public UnidadMedida FindById(Long idunimedi);
	
	public UnidadMedida save(UnidadMedida unimedi);

}
