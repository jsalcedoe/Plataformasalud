package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.OrdenProcedimientoExamen;

public interface IOrdenProcedimientoExamenService {
	
	public List<OrdenProcedimientoExamen> findAll();
	
	public OrdenProcedimientoExamen findById(Long idordprocexam);
	
	public OrdenProcedimientoExamen save (OrdenProcedimientoExamen ordprocesxam);
	
	public List<OrdenProcedimientoExamen> findByEventpordprocexam_fk(Long idevent);

}
