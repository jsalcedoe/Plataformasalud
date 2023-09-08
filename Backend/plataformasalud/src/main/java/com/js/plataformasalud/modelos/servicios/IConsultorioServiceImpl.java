package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IConsultorioDao;
import com.js.plataformasalud.modelos.entidades.Consultorio;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IConsultorioServiceImpl implements IConsultorioService {
	
	private IConsultorioDao consDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Consultorio> findAll() {
		
		return (List<Consultorio>) consDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Consultorio FindById(Long idconsultorio) {
		
		return consDao.findById(idconsultorio).orElse(null);
	}

	@Override
	@Transactional
	public Consultorio save(Consultorio consultorio) {
		
		return consDao.save(consultorio);
	}

	@Override
	@Transactional
	public void delete(Long idconsultorio) {
	
		consDao.deleteById(idconsultorio);
	}

}
