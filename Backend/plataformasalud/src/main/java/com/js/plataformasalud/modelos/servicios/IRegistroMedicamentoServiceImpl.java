package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IRegistroMedicamentoDao;
import com.js.plataformasalud.modelos.entidades.RegistroMedicamento;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class IRegistroMedicamentoServiceImpl implements IRegistroMedicamentoService {
	
	private IRegistroMedicamentoDao regmedinsdao;
	
	@Override
	@Transactional(readOnly = true)
	public List<RegistroMedicamento> findAll() {
		
		return (List<RegistroMedicamento>) regmedinsdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public RegistroMedicamento findById(Long idregmedins) {
		
		return regmedinsdao.findById(idregmedins).orElse(null);
	}

	@Override
	@Transactional
	public RegistroMedicamento save(RegistroMedicamento regmedins) {
		
		return regmedinsdao.save(regmedins);
	}

}
