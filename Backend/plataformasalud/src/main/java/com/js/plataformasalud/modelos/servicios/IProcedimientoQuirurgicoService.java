package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.DescripcionQuirurgica;

public interface IProcedimientoQuirurgicoService {
	
	public List<DescripcionQuirurgica> findAll();
	
	public DescripcionQuirurgica findById(Long idqx);
	
	public DescripcionQuirurgica save(DescripcionQuirurgica qx);
	

}
