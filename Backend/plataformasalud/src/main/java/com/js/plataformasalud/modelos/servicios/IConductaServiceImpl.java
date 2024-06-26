package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IConductaDao;
import com.js.plataformasalud.modelos.entidades.Conducta;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class IConductaServiceImpl implements IConductaService{
	
	private IConductaDao condpacdao;

	@Override
	@Transactional(readOnly = true)
	public List<Conducta> findAll() {
		
		return (List<Conducta>) condpacdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Conducta FindById(Long idcondpac) {
		
		return condpacdao.findById(idcondpac).orElse(null);
	}

	@Override
	@Transactional
	public Conducta save(Conducta condpac) {
		
		return condpacdao.save(condpac);
	}

}
