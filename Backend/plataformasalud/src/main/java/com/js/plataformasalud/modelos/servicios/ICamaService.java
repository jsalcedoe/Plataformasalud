package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.Camas;

public interface ICamaService {
	
	public List<Camas> findAll();
	
	public Camas findById (Long idhab);
	
	public Camas save (Camas cam);
	
	

}
