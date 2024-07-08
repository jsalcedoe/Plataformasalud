package com.js.plataformasalud.modelos.servicios;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IProcedimientoQuirurgicoDao;
import com.js.plataformasalud.modelos.entidades.DescripcionQuirurgica;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor


public class IProcedimientoQuirurgicoServiceImpl implements IProcedimientoQuirurgicoService{
	
	
	private IProcedimientoQuirurgicoDao qxdao;

	@Override
	@Transactional(readOnly = true)
	public List<DescripcionQuirurgica> findAll() {
		
		return (List<DescripcionQuirurgica>) qxdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public DescripcionQuirurgica findById(Long idqx) {
		
		return qxdao.findById(idqx).orElse(null);
	}

	@Override
	@Transactional
	public DescripcionQuirurgica save(DescripcionQuirurgica qx) {
		
		return qxdao.save(qx);
	}
}
