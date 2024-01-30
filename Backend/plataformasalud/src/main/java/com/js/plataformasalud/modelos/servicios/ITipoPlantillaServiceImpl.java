package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.ITipoPlantillaDao;
import com.js.plataformasalud.modelos.entidades.TipoPlantilla;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class ITipoPlantillaServiceImpl implements ITipoPlantillaService {
	
	private ITipoPlantillaDao tytempdao;
	
	@Override
	@Transactional(readOnly = true)
	public List<TipoPlantilla> findAll() {
		
		return (List<TipoPlantilla>) tytempdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public TipoPlantilla findById(Long idtytemp) {
		
		return tytempdao.findById(idtytemp).orElse(null);
	}

	@Override
	@Transactional
	public TipoPlantilla save(TipoPlantilla tytemp) {
		
		return tytempdao.save(tytemp);
	}

}
