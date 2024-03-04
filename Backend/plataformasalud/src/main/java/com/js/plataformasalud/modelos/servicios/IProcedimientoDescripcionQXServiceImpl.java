package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IProcedimientoDescripcionQXDao;
import com.js.plataformasalud.modelos.entidades.ProcedimientoDescripcionQX;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class IProcedimientoDescripcionQXServiceImpl implements IProcedimientoDescripcionQXServices {
	
	private IProcedimientoDescripcionQXDao procdescqxdao;
	
	@Override
	@Transactional(readOnly = true)
	public List<ProcedimientoDescripcionQX> findAll() {
		
		return (List<ProcedimientoDescripcionQX>)procdescqxdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public ProcedimientoDescripcionQX findById(Long idprocqx) {
		
		return procdescqxdao.findById(idprocqx).orElse(null);
	}

	@Override
	@Transactional
	public ProcedimientoDescripcionQX save(ProcedimientoDescripcionQX procdesqx) {
		
		return procdescqxdao.save(procdesqx);
	}

}
