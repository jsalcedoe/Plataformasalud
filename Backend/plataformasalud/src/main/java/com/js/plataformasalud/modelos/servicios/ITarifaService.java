package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.Tarifa;

public interface ITarifaService {
	
	public List<Tarifa> findAll();
	
	public Tarifa findById(long idtarifa);
	
	public Tarifa save(Tarifa tarifa);
	
	

}
