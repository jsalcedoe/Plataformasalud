package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.AsignaCitaDao;
import com.js.plataformasalud.modelos.entidades.AsignaCita;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class IAsignaCitaServiceImpl implements IAsignaCitaService {
	
	private AsignaCitaDao ascitdao;
	
	@Override
	@Transactional(readOnly = true)
	public List<AsignaCita> findAll() {
		
		return (List<AsignaCita>) ascitdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public AsignaCita findById(Long idascit) {
		
		return ascitdao.findById(idascit).orElse(null);
	}

	@Override
	@Transactional
	public AsignaCita save(AsignaCita ascit) {
		
		return ascitdao.save(ascit);
	}

}
