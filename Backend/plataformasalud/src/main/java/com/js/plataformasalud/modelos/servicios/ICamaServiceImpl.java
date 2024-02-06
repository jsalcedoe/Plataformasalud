package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.ICamasDao;
import com.js.plataformasalud.modelos.entidades.Camas;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class ICamaServiceImpl implements ICamaService {
	
	private ICamasDao camdao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Camas> findAll() {
		
		return (List<Camas>) camdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Camas findById(Long idhab) {
		
		return camdao.findById(idhab).orElse(null);
	}

	@Override
	@Transactional
	public Camas save(Camas cam) {
		
		return camdao.save(cam);
	}

}
