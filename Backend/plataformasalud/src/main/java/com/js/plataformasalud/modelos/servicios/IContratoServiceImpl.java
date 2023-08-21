package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IContratoDao;
import com.js.plataformasalud.modelos.entidades.Contrato;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IContratoServiceImpl implements IContratoService {
	
	private IContratoDao contratodao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Contrato> findAll() {
		
		return (List<Contrato>) contratodao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Contrato findById(Long idcontrato) {
		
		return contratodao.findById(idcontrato).orElse(null);
	}

	@Override
	@Transactional
	public Contrato save(Contrato contrato) {
		
		return contratodao.save(contrato);
	}

	@Override
	@Transactional
	public void delete(Long idcontrato) {
		contratodao.deleteById(idcontrato);
		
	}

}
