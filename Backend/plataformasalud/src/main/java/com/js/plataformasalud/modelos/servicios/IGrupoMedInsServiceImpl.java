package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IGrupoMedInsDao;
import com.js.plataformasalud.modelos.entidades.GrupoMedIns;

@Service
public class IGrupoMedInsServiceImpl implements IGrupoMedInsService {
	
	@Autowired 
	private IGrupoMedInsDao medinsdao;

	@Override
	@Transactional(readOnly = true)
	public List<GrupoMedIns> findAll() {
		
		return (List<GrupoMedIns>) medinsdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public GrupoMedIns findById(Long idgrupmedins) {
		
		return medinsdao.findById(idgrupmedins).orElse(null);
	}

	@Override
	@Transactional
	public GrupoMedIns save(GrupoMedIns grupmedins) {
		
		return medinsdao.save(grupmedins);
	}

	@Override
	@Transactional
	public void delete(Long idgrupmedins) {
		
		medinsdao.deleteById(idgrupmedins);
	}

}
