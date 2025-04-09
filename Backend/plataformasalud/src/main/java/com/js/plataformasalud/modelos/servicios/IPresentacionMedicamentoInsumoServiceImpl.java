package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IPresentacionMedicamentoInsumoDao;
import com.js.plataformasalud.modelos.entidades.PresentacionMedicamentoInsumo;


import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IPresentacionMedicamentoInsumoServiceImpl implements IPresentacionMedicamentoInsumoService{
	
	private IPresentacionMedicamentoInsumoDao pmedinsdao;

	@Override
	@Transactional(readOnly = true)
	public List<PresentacionMedicamentoInsumo> findAll() {
		
		return (List<PresentacionMedicamentoInsumo>) pmedinsdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public PresentacionMedicamentoInsumo FindById(Long idpmedins) {
		
		return pmedinsdao.findById(idpmedins).orElse(null);
	}

	@Override
	@Transactional
	public PresentacionMedicamentoInsumo save(PresentacionMedicamentoInsumo pmedins) {
		
		return pmedinsdao.save(pmedins);
	}

	@Override
	public List<PresentacionMedicamentoInsumo> findByPmedins(String detpmedins) {
		
		return (List<PresentacionMedicamentoInsumo>) pmedinsdao.findByPmedins(detpmedins);
	}

	
}
