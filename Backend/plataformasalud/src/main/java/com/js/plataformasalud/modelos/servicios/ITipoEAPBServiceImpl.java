package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.ITipoEAPBDao;
import com.js.plataformasalud.modelos.entidades.TipoEAPB;

@Service

public class ITipoEAPBServiceImpl implements ITipoEAPBService {
	
	private ITipoEAPBDao tipentdao;
	
	

	public ITipoEAPBServiceImpl(ITipoEAPBDao tipentdao) {
		super();
		this.tipentdao = tipentdao;
	}

	@Override
	@Transactional(readOnly = true)
	public List<TipoEAPB> findAll() {
		
		return (List<TipoEAPB>) tipentdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public TipoEAPB findById(long idtipeapb) {
		
		return tipentdao.findById(idtipeapb).orElse(null);
	}

	@Override
	@Transactional
	public TipoEAPB save(TipoEAPB tipeapb) {
		
		return tipentdao.save(tipeapb);
	}

	@Override
	@Transactional
	public void delete(long idtipeapb) {
		
		tipentdao.deleteById(idtipeapb);
	}

}
