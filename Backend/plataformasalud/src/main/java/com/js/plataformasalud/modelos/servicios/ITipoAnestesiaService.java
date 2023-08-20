package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.TipoAnestesia;

public interface ITipoAnestesiaService {
	
	public List<TipoAnestesia> findAll();
	
	public TipoAnestesia findById(Long idtipanest);
	
	public TipoAnestesia save (TipoAnestesia anestesia);
	
	public void delete(Long idtipanest);

}
