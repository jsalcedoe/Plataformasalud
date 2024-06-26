package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IPrestadorServicioDao;
import com.js.plataformasalud.modelos.entidades.PrestadorServicio;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class IPrestadorServicioServiceImpl implements IPrestadorServicioService {
	
	private IPrestadorServicioDao preservdao;

	@Override
	@Transactional(readOnly = true)
	public List<PrestadorServicio> findAll() {
		
		return (List <PrestadorServicio>) preservdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public PrestadorServicio findById(Long idprestserv) {
		
		return preservdao.findById(idprestserv).orElse(null);
	}

	@Override
	@Transactional
	public PrestadorServicio save(PrestadorServicio prestserv) {
		
		return preservdao.save(prestserv);
	}

	@Override
	public void delete(Long idprestserv) {
		
		preservdao.deleteById(idprestserv);
		
	}

}
