package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IProcedimientoExamenesDao;
import com.js.plataformasalud.modelos.entidades.ProcedimientosExamenes;

@Service
public class IProcedimientoExamenesImpl implements IProcedimientoExamenesServices {
	
	@Autowired
	private IProcedimientoExamenesDao procexamdao;

	@Override
	@Transactional(readOnly = true)
	public List<ProcedimientosExamenes> findAll() {
		
		return (List<ProcedimientosExamenes>)procexamdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public ProcedimientosExamenes findById(String codprocexam) {
		
		return procexamdao.findById(codprocexam).orElse(null);
	}

	@Override
	@Transactional
	public ProcedimientosExamenes save(ProcedimientosExamenes procexam) {
		
		return procexamdao.save(procexam);
	}

	@Override
	@Transactional
	public void delete(String codprocexam) {
		
		procexamdao.deleteById(codprocexam);
	}

}
