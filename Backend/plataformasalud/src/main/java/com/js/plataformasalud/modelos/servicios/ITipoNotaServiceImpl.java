package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.ITipoNotaDao;
import com.js.plataformasalud.modelos.entidades.TipoNota;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class ITipoNotaServiceImpl implements ITipoNotaService {
	
	private ITipoNotaDao typnotdao;
	
	@Override
	@Transactional(readOnly = true)
	public List<TipoNota> findAll() {
		
		return (List<TipoNota>) typnotdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public TipoNota findById(Long idtypnot) {
		
		return typnotdao.findById(idtypnot).orElse(null);
	}

	@Override
	@Transactional
	public TipoNota save(TipoNota typnot) {
		
		return typnotdao.save(typnot);
	}

}
