package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.Contrato;

public interface IContratoService {
	
	public List<Contrato> findAll();
	
	public Contrato findById(Long idcontrato);
	
	public Contrato save (Contrato contrato);
	
	public void delete (Long idcontrato);

}
