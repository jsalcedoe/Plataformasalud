package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IOrdenesMedicamentoInsumoDao;
import com.js.plataformasalud.modelos.entidades.OrdenesMedicamentosInsumos;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class IOrdenMedicamentoInsumoServiceImpl implements IOrdeneMedicamentoInsumoService {
	
	private IOrdenesMedicamentoInsumoDao ordmedinsdao;
	
	@Override
	@Transactional(readOnly = true)
	public List<OrdenesMedicamentosInsumos> findAll() {
		
		return (List<OrdenesMedicamentosInsumos>) ordmedinsdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public OrdenesMedicamentosInsumos findById(Long idordmedins) {
		
		return ordmedinsdao.findById(idordmedins).orElse(null);
	}

	@Override
	@Transactional
	public OrdenesMedicamentosInsumos save(OrdenesMedicamentosInsumos ordmedins) {
		
		return ordmedinsdao.save(ordmedins);
	}

}
