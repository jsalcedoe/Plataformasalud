package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.TipoNota;

public interface ITipoNotaService {
	
	public List<TipoNota> findAll();
	
	public TipoNota findById(Long idtypnot);
	
	public TipoNota save (TipoNota typnot);
	
	public List<TipoNota> findByTipoNota(String dettypnot);

}
