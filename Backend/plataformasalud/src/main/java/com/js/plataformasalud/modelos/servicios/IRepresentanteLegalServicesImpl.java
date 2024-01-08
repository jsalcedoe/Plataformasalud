package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IRepresentanteLegalDao;
import com.js.plataformasalud.modelos.entidades.RepresentanteLegal;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class IRepresentanteLegalServicesImpl implements IRepresentanteLegalService{
	
	private IRepresentanteLegalDao replegdao;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<RepresentanteLegal> findAll() {
		
		return (List <RepresentanteLegal>)replegdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public RepresentanteLegal findById(Long idrepleg) {
		
		return replegdao.findById(idrepleg).orElse(null);
	}

	@Override
	public RepresentanteLegal save(RepresentanteLegal replegal) {
		
		return replegdao.save(replegal);
	}

	@Override
	public void delete(Long idrepleg) {
		
		replegdao.deleteById(idrepleg);
		
	}

}
