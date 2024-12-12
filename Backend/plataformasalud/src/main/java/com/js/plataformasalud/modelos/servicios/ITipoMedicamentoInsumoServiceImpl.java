package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.ITipoMedicamentoInsumoDao;
import com.js.plataformasalud.modelos.entidades.TipoMedicamentoInsumo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ITipoMedicamentoInsumoServiceImpl implements ITipoMedicamentoInsumoService{
	
	private ITipoMedicamentoInsumoDao tmedinsdao;
	
	@Override
	@Transactional(readOnly = true)
	public List<TipoMedicamentoInsumo> findAll() {
		
		return (List<TipoMedicamentoInsumo>)tmedinsdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public TipoMedicamentoInsumo FindById(Long idtmedins) {
		
		return tmedinsdao.findById(idtmedins).orElse(null);
	}

	@Override
	@Transactional
	public TipoMedicamentoInsumo save(TipoMedicamentoInsumo tmedins) {
		
		return tmedinsdao.save(tmedins);
	}

}
