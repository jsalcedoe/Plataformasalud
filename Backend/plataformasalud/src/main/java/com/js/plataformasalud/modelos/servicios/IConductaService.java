package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.Conducta;

public interface IConductaService {
	
	public List<Conducta> findAll();
	
	public Conducta FindById(Long idcondpac);
	
	public Conducta save(Conducta condpac);

}
