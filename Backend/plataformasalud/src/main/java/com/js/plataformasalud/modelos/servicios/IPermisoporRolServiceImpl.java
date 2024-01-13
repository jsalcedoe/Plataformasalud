package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IPermisoporRolDao;
import com.js.plataformasalud.modelos.entidades.PermisoporRol;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class IPermisoporRolServiceImpl implements IPermisoporRolService {
	
	private IPermisoporRolDao permroldao;
		
	@Override
	@Transactional(readOnly = true)
	public List<PermisoporRol> findAll() {
		
		return (List <PermisoporRol>) permroldao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public PermisoporRol findById(Long idpermrol) {
		
		return permroldao.findById(idpermrol).orElse(null);
	}

	@Override
	@Transactional
	public PermisoporRol save(PermisoporRol permrol) {
		
		return permroldao.save(permrol);
	}

}
