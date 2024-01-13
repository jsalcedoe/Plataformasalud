package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IRolDao;
import com.js.plataformasalud.modelos.entidades.Rol;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class IRolServiceImpl implements IRolService{
	
	private IRolDao roldao;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Rol> findAll() {
		
		return (List <Rol>) roldao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Rol findById(Long idrol) {
		
		return roldao.findById(idrol).orElse(null);
	}

	@Override
	public Rol save(Rol rol) {
		
		return roldao.save(rol);
	}
	
	
}
