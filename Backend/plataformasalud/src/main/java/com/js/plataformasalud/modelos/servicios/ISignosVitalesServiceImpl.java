package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.ISignosVitalesDao;
import com.js.plataformasalud.modelos.entidades.SignosVitales;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class ISignosVitalesServiceImpl implements ISignosVitalesService {
	
	private ISignosVitalesDao sigvitdao;
	
	@Override
	@Transactional(readOnly = true)
	public List<SignosVitales> findAll() {
		return (List<SignosVitales>) sigvitdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public SignosVitales findById(Long idsigvit) {
		return sigvitdao.findById(idsigvit).orElse(null);
	}

	@Override
	@Transactional
	public SignosVitales save(SignosVitales sigvit) {
		return sigvitdao.save(sigvit);
	}

}
