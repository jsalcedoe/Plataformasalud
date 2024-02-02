package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IPlantillaDao;
import com.js.plataformasalud.modelos.entidades.Plantillas;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class IPlantillasServiceImpl implements IPlantillasService{
	
	private IPlantillaDao tempdao;

	@Override
	@Transactional(readOnly = true)
	public List<Plantillas> findAll() {
		
		return (List<Plantillas>) tempdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Plantillas findById(Long idtemp) {
		
		return tempdao.findById(idtemp).orElse(null);
	}

	@Override
	@Transactional
	public Plantillas save(Plantillas temp) {
		
		return tempdao.save(temp);
	}

}
