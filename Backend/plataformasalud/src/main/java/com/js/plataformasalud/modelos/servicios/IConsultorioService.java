package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.Consultorio;

public interface IConsultorioService {

	public List<Consultorio> findAll();
	
	public Consultorio FindById(Long idconsultorio);
	
	public Consultorio save (Consultorio consultorio);
	
	public void delete (Long idconsultorio);

}
