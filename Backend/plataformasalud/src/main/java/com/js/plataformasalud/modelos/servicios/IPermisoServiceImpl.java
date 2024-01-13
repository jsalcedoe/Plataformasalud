package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IPermisoDao;
import com.js.plataformasalud.modelos.entidades.Permiso;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class IPermisoServiceImpl implements IPermisoServices {
	
	
	private IPermisoDao permdao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Permiso> findAll() {
		
		return (List<Permiso>) permdao.findAll() ;
	}

	@Override
	@Transactional(readOnly = true)
	public Permiso findById(Long idperm) {
		
		return permdao.findById(idperm).orElse(null); 
	}

	@Override
	public Permiso save(Permiso permiso) {
		
		return permdao.save(permiso);
	}

}
