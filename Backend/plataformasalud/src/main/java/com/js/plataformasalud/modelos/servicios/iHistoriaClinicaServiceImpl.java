package com.js.plataformasalud.modelos.servicios;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IHistoriaClinicaDao;
import com.js.plataformasalud.modelos.entidades.HistoriaClinica;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class IHistoriaClinicaServiceImpl implements IHistoriaClinicaService {
	
	private IHistoriaClinicaDao hcpacdao;

	@Override
	@Transactional(readOnly = true)
	public List<HistoriaClinica> findAll() {
		
		return (List <HistoriaClinica>) hcpacdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public HistoriaClinica findById(Long idhcpac) {
		
		return hcpacdao.findById(idhcpac).orElse(null);
	}

	@Override
	@Transactional
	public HistoriaClinica save(HistoriaClinica hcpac) {
		
		return hcpacdao.save(hcpac);
	}

}
