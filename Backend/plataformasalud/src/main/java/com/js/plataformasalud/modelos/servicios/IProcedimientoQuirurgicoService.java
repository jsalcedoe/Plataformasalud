package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.ProcedimientoQuirurgico;

public interface IProcedimientoQuirurgicoService {
	
	public List<ProcedimientoQuirurgico> findAll();
	
	public ProcedimientoQuirurgico findById(Long idqx);
	
	public ProcedimientoQuirurgico save(ProcedimientoQuirurgico qx);

}
