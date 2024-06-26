package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.ITipoHeridaDao;
import com.js.plataformasalud.modelos.entidades.TipoHerida;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service

public class ITipoHeridaServiceImpl implements ITipoHeridaService {
	
	private ITipoHeridaDao thxdao;

	@Override
	@Transactional(readOnly = true)
	public List<TipoHerida> findAll() {
		
		return (List<TipoHerida>) thxdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public TipoHerida findById(Long idthx) {
		
		return thxdao.findById(idthx).orElse(null);
	}

	@Override
	@Transactional
	public TipoHerida save(TipoHerida thx) {
		
		return thxdao.save(thx);
	}

}
