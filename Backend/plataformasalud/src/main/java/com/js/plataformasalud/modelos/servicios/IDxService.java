package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.Diagnostico;

public interface IDxService {
	public List<Diagnostico> findAll();
	
	public Diagnostico findById(String iddx);
	
	public Diagnostico save (Diagnostico dx);
	
	

}
