package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.RegistroEstancia;

public interface IRegistroEstanciaService {
	
	public List<RegistroEstancia> findAll();
	
	public RegistroEstancia findById(Long idregest);
	
	public RegistroEstancia save (RegistroEstancia regest);

}
