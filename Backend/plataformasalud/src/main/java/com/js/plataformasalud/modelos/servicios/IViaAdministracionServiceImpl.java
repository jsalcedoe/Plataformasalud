package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IViaAdministracionDao;
import com.js.plataformasalud.modelos.entidades.ViaAdministracion;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class IViaAdministracionServiceImpl implements IViaAdministracionService {
	
	private IViaAdministracionDao viadmdao;
	
	@Override
	@Transactional(readOnly = true)
	public List<ViaAdministracion> findAll() {
		
		return (List<ViaAdministracion>) viadmdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public ViaAdministracion findById(Long idviadm) {
		
		return viadmdao.findById(idviadm).orElse(null);
	}

	@Override
	@Transactional
	public ViaAdministracion save(ViaAdministracion viadm) {
		
		return viadmdao.save(viadm);
	}

}
