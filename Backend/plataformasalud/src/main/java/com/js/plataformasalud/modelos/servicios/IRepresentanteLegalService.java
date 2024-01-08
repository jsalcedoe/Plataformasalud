package com.js.plataformasalud.modelos.servicios;

import java.util.List;


import com.js.plataformasalud.modelos.entidades.RepresentanteLegal;

public interface IRepresentanteLegalService {
	
	public List<RepresentanteLegal> findAll();
	
	public RepresentanteLegal findById(Long idrepleg);
	
	public RepresentanteLegal save (RepresentanteLegal replegal);
	
	public void delete (Long idrepleg);

}
