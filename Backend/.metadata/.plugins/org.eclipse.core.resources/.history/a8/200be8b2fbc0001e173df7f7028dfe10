package com.js.plataformasalud.modelos.servicios;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IProcedimientoQuirurgicoDao;
import com.js.plataformasalud.modelos.entidades.ProcedimientoQuirurgico;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IProcedimientoQuirurgicoServiceImpl implements IProcedimientoQuirurgicoService{
	
	
	private IProcedimientoQuirurgicoDao qxdao;

	@Override
	@Transactional(readOnly = true)
	public List<ProcedimientoQuirurgico> findAll() {
		
		return (List<ProcedimientoQuirurgico>) qxdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public ProcedimientoQuirurgico findById(Long idqx) {
		
		return qxdao.findById(idqx).orElse(null);
	}

	@Override
	@Transactional
	public ProcedimientoQuirurgico save(ProcedimientoQuirurgico qx) {
		
		return qxdao.save(qx);
	}
}
