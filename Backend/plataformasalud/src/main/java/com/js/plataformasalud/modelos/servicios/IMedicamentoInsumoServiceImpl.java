package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.js.plataformasalud.modelos.dao.IMedicamentoInsumoDao;
import com.js.plataformasalud.modelos.entidades.MedicamentoInsumo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class IMedicamentoInsumoServiceImpl implements IMedicamentoInsumoService {
	
	private IMedicamentoInsumoDao medinsdao;
	
	@Override
	@Transactional(readOnly = true)
	public List<MedicamentoInsumo> findAll() {
		
		return (List<MedicamentoInsumo>) medinsdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public MedicamentoInsumo findById(Long idmedins) {
		
		return medinsdao.findById(idmedins).orElse(null);
	}

	@Override
	@Transactional
	public MedicamentoInsumo save(MedicamentoInsumo medins) {
		
		return medinsdao.save(medins);
	}

}
