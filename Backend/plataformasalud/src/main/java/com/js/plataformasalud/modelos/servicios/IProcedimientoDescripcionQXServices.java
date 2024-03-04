package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.ProcedimientoDescripcionQX;

public interface IProcedimientoDescripcionQXServices {
	
	public List<ProcedimientoDescripcionQX> findAll();
	
	public ProcedimientoDescripcionQX findById(Long idprocqx);
	
	public ProcedimientoDescripcionQX save (ProcedimientoDescripcionQX procdesqx);

}
