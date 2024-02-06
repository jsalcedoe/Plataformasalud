package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IRegistroEstanciaDao;
import com.js.plataformasalud.modelos.entidades.RegistroEstancia;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class IRegistroEstanciaServiceImpl implements IRegistroEstanciaService {
	
	private IRegistroEstanciaDao regestdao;
	
	@Override
	@Transactional(readOnly = true)
	public List<RegistroEstancia> findAll() {
		
		return (List<RegistroEstancia>) regestdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public RegistroEstancia findById(Long idregest) {
		
		return regestdao.findById(idregest).orElse(null);
	}

	@Override
	@Transactional
	public RegistroEstancia save(RegistroEstancia regest) {
		
		return regestdao.save(regest);
	}

}
