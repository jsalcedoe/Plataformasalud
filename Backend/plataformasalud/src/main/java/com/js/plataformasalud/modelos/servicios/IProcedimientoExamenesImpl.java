package com.js.plataformasalud.modelos.servicios;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IProcedimientoExamenesDao;
import com.js.plataformasalud.modelos.entidades.ProcedimientosExamenes;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IProcedimientoExamenesImpl implements IProcedimientoExamenesServices {
	
	
	private IProcedimientoExamenesDao pxexdao;

	@Override
	@Transactional(readOnly = true)
	public List<ProcedimientosExamenes> findAll() {
		
		return (List<ProcedimientosExamenes>)pxexdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public ProcedimientosExamenes findById(Long idpxex) {
		
		return pxexdao.findById(idpxex).orElse(null);
	}

	@Override
	@Transactional
	public ProcedimientosExamenes save(ProcedimientosExamenes procexam) {
		
		return pxexdao.save(procexam);
	}

	@Override
	public List<ProcedimientosExamenes> findBynompxex(String nompxex) {
		
		return (List<ProcedimientosExamenes>)pxexdao.findBynompxex(nompxex);
	}
	

	

}
