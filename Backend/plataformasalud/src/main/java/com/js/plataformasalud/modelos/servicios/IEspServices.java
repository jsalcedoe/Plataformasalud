package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.Especialidades;

public interface IEspServices {
	
	public List<Especialidades> findAll();
	
	public Especialidades findById(Long idepecialidades);
	
	public Especialidades save(Especialidades especialidades);
	
	public void delete (Long idespecialidades);

}
