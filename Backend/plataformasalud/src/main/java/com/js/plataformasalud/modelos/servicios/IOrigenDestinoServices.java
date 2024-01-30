package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.OrigenDestino;

public interface IOrigenDestinoServices {
	
	public List<OrigenDestino> findAll();
	
	public OrigenDestino findById(Long idorgdes);
	
	public OrigenDestino save (OrigenDestino orgdes);

}
