package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.TipoProcedimiento;

public interface ITipoProcService {
	
	public List<TipoProcedimiento> findAll();
	
	public TipoProcedimiento findById(Long idtproc);
	
	public TipoProcedimiento save (TipoProcedimiento tproc);
	
	public void delete (Long idtproc);
	
	public List<TipoProcedimiento> findByTipoProcexam(String detproc);

}
