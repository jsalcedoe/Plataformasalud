package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IEstadoDao;
import com.js.plataformasalud.modelos.entidades.Estado;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IEstadoServiceImpl implements IEstadoService{
	
	private IEstadoDao statusdao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Estado> findAll() {
		
		return (List <Estado>) statusdao.findAll() ;
	}

	@Override
	@Transactional(readOnly = true)
	public Estado findById(Long idstatus) {
		
		return statusdao.findById(idstatus).orElse(null);
	}

	@Override
	@Transactional
	public Estado save(Estado estado) {
		
		return statusdao.save(estado);
	}

}
