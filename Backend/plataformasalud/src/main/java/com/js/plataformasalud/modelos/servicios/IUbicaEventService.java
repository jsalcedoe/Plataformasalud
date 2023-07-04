package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.UbicacionEvent;

public interface IUbicaEventService {
	public List<UbicacionEvent> findAll();
	
	public UbicacionEvent findById(Long idubieventpac);
	
	public UbicacionEvent save (UbicacionEvent ubicaevent);
	
	public void delete (Long idubieventpac);

}
