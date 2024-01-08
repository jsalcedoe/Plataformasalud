package com.js.plataformasalud.modelos.servicios;

import java.util.List;


import com.js.plataformasalud.modelos.entidades.PrestadorServicio;

public interface IPrestadorServicioService {
	
	public List<PrestadorServicio> findAll();
	
	public PrestadorServicio findById(Long idprestserv);
	
	public PrestadorServicio save (PrestadorServicio prestserv);
	
	public void delete (Long idprestserv);

}
