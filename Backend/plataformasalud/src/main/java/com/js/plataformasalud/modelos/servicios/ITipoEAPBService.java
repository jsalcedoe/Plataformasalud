package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.TipoEAPB;

public interface ITipoEAPBService {
	
	public List<TipoEAPB> findAll();
	
	public TipoEAPB findById(long idtipeapb);
	
	public TipoEAPB save (TipoEAPB tipeapb);

}
