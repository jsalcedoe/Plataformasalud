package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.ITarifaDao;
import com.js.plataformasalud.modelos.entidades.Tarifa;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class ITarifaServiceImpl implements ITarifaService {
	
	private ITarifaDao tarifadao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Tarifa> findAll() {
		
		return (List<Tarifa>) tarifadao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Tarifa findById(long idtarifa) {
		
		return tarifadao.findById(idtarifa).orElse(null);
	}

	@Override
	public Tarifa save(Tarifa tarifa) {
		
		return tarifadao.save(tarifa);
	}

	@Override
	public void delete(long idtarifa) {
		
		tarifadao.deleteById(idtarifa);
		
	}

}
