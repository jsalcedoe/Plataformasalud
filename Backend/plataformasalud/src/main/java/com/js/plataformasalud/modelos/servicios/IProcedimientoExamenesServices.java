package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.ProcedimientosExamenes;

public interface IProcedimientoExamenesServices {
	
public List<ProcedimientosExamenes> findAll();
	
	public ProcedimientosExamenes findById(Long idpxex);
	
	public ProcedimientosExamenes save (ProcedimientosExamenes procexam);
	
	public List<ProcedimientosExamenes> findBynompxex (String nompxex);

}
