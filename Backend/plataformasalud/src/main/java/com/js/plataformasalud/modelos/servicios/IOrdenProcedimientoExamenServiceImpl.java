package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.js.plataformasalud.modelos.dao.IOrdenProcedimientoExamenDao;
import com.js.plataformasalud.modelos.entidades.OrdenProcedimientoExamen;

import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service

public class IOrdenProcedimientoExamenServiceImpl implements IOrdenProcedimientoExamenService {
	
	private IOrdenProcedimientoExamenDao ordprocexamdao;
	
	@Override
	@Transactional(readOnly=true)
	public List<OrdenProcedimientoExamen> findAll() {
		
		return (List<OrdenProcedimientoExamen>) ordprocexamdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public OrdenProcedimientoExamen findById(Long idordprocexam) {
		
		return ordprocexamdao.findById(idordprocexam).orElse(null);
	}

	@Override
	@Transactional
	public OrdenProcedimientoExamen save(OrdenProcedimientoExamen ordprocesxam) {
		
		return ordprocexamdao.save(ordprocesxam);
	}

	@Override
	@Transactional(readOnly = true)
	public List<OrdenProcedimientoExamen> findByEventpordprocexam_fk(Long idevent) {
		
		return (List<OrdenProcedimientoExamen>) ordprocexamdao.findByEventpordprocexam_fk(idevent);
	}
	

}
