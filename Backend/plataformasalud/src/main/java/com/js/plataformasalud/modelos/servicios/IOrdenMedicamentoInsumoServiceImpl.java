package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IOrdenMedicamentoInsumoDao;
import com.js.plataformasalud.modelos.entidades.OrdenMedicamentoInsumo;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service

public class IOrdenMedicamentoInsumoServiceImpl implements IOrdenMedicamentoInsumoService {
	
	private IOrdenMedicamentoInsumoDao ordmedinsdao;
	
	@Override
	@Transactional(readOnly = true)
	public List<OrdenMedicamentoInsumo> findAll() {
		
		return (List<OrdenMedicamentoInsumo>)ordmedinsdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public OrdenMedicamentoInsumo findById(Long idordmedins) {
		
		return ordmedinsdao.findById(idordmedins).orElse(null);
	}

	@Override
	public OrdenMedicamentoInsumo save(OrdenMedicamentoInsumo ordmedins) {
		
		return ordmedinsdao.save(ordmedins);
	}

	@Override
	@Transactional(readOnly = true)
	public List<OrdenMedicamentoInsumo> findByEventordmedins_fk(Long idevent) {
		
		return (List<OrdenMedicamentoInsumo>)ordmedinsdao.findByEventordmedins_fk(idevent);
	}

}
