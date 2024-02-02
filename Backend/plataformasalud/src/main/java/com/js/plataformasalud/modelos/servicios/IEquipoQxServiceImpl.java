package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IEquipoQxDao;
import com.js.plataformasalud.modelos.entidades.EquipoQx;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class IEquipoQxServiceImpl implements IEquipoQxService {
	
	private IEquipoQxDao eqqxdao;

	@Override
	@Transactional(readOnly = true)
	public List<EquipoQx> findAll() {
		
		return (List<EquipoQx>) eqqxdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public EquipoQx findById(Long ideqqx) {
		
		return eqqxdao.findById(ideqqx).orElse(null);
	}

	@Override
	@Transactional
	public EquipoQx save(EquipoQx eqqx) {
		
		return eqqxdao.save(eqqx);
	}

}
