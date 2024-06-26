package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.TipoHerida;

public interface ITipoHeridaService {
	
	public List<TipoHerida> findAll();
	
	public TipoHerida findById(Long idthx);
	
	public TipoHerida save(TipoHerida thx);

}
