package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IProcedimientoQuirurgicoDao;
import com.js.plataformasalud.modelos.entidades.ProcedimientoQuirurgico;

@Service
public class IProcedimientoQuirurgicoServiceImpl implements IProcedimientoQuirurgicoService{
	
	@Autowired
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

	@Override
	@Transactional
	public void delete(Long idqx) {
		
		qxdao.deleteById(idqx);
	}
}
