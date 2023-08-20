package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.ProcedimientosExamenes;

public interface IProcedimientoExamenesServices {
	
public List<ProcedimientosExamenes> findAll();
	
	public ProcedimientosExamenes findById(String codprocexam);
	
	public ProcedimientosExamenes save (ProcedimientosExamenes procexam);
	
	public void delete (String codprocexam);

}
