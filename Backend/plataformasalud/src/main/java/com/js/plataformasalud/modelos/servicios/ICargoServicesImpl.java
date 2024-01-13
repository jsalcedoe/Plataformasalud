package com.js.plataformasalud.modelos.servicios;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.ICargoDao;
import com.js.plataformasalud.modelos.entidades.Cargo;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class ICargoServicesImpl implements ICargoServices {
	
	private ICargoDao cargdao;

	@Override
	@Transactional(readOnly = true)
	public List<Cargo> findAll() {
		
		return (List<Cargo>)cargdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Cargo findById(Long idcarguser) {
		
		return cargdao.findById(idcarguser).orElse(null);
	}

	@Override
	@Transactional
	public Cargo save(Cargo especialidades) {
		
		return cargdao.save(especialidades);
	}

	@Override
	@Transactional
	public void delete(Long idcarguser) {
		cargdao.deleteById(idcarguser);
		
	}

}
