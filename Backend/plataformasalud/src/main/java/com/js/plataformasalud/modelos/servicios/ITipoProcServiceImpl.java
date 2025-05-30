package com.js.plataformasalud.modelos.servicios;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.Itipoprocdao;
import com.js.plataformasalud.modelos.entidades.TipoProcedimiento;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ITipoProcServiceImpl implements ITipoProcService {
	
	private Itipoprocdao tproc;
	
	@Override
	@Transactional(readOnly = true)
	public List<TipoProcedimiento> findAll() {
		
		return (List<TipoProcedimiento>) tproc.findAll() ;
	}

	@Override
	@Transactional(readOnly = true)
	public TipoProcedimiento findById(Long idtproc) {
		
		return tproc.findById(idtproc).orElse(null);
	}

	@Override
	@Transactional
	public TipoProcedimiento save(TipoProcedimiento tipoproc) {
		
		return tproc.save(tipoproc);
	}

	@Override
	@Transactional
	public void delete(Long idtproc) {
		tproc.deleteById(idtproc);
	}

	@Override
	public List<TipoProcedimiento> findByTipoProcexam(String detproc) {
		
		return (List<TipoProcedimiento>)tproc.findByTipoProcexam(detproc);
	}
	
	
}
