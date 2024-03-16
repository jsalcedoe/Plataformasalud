package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IDepartamentoDao;
import com.js.plataformasalud.modelos.entidades.Departamento;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IDepartamentoServiceImpl implements IDepartamentoService {
	
	private IDepartamentoDao depdao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Departamento> findAll() {
		
		return (List<Departamento>)depdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Departamento findById(Long iddep) {
		
		return depdao.findById(iddep).orElse(null);
	}

	@Override
	@Transactional
	public Departamento save(Departamento departamento) {
		
		return depdao.save(departamento);
	}


}
