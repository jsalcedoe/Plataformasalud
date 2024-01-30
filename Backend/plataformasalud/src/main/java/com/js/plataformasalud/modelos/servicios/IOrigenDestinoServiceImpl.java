package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IOrigenDestinoDao;
import com.js.plataformasalud.modelos.entidades.OrigenDestino;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class IOrigenDestinoServiceImpl implements IOrigenDestinoServices {
	
	private IOrigenDestinoDao orgdesdao;
	
	@Override
	@Transactional(readOnly = true)
	public List<OrigenDestino> findAll() {
		
		return (List<OrigenDestino>)orgdesdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public OrigenDestino findById(Long idorgdes) {

		return orgdesdao.findById(idorgdes).orElse(null);
	}
	@Override
	@Transactional
	public OrigenDestino save(OrigenDestino orgdes) {
		
		return orgdesdao.save(orgdes);
	}

}
