package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.ITarifaContratoDao;
import com.js.plataformasalud.modelos.entidades.TarifaContrato;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ITarifaContratoServiceImpl implements ITarifaContratoService {
	
	private ITarifaContratoDao taricontdao;
	
	@Override
	@Transactional(readOnly = true)
	public List<TarifaContrato> findAll() {
		
		return (List<TarifaContrato>) taricontdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public TarifaContrato findById(Long idtarifcont) {
		
		return taricontdao.findById(idtarifcont).orElse(null);
	}

	@Override
	@Transactional
	public TarifaContrato save(TarifaContrato tarifcont) {
		
		return taricontdao.save(tarifcont);
	}

	@Override
	@Transactional
	public void delete(Long idtarifcont) {
		taricontdao.deleteById(idtarifcont);
		
	}

}
