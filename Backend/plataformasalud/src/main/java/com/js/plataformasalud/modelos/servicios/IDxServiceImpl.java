package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.IDxDao;
import com.js.plataformasalud.modelos.entidades.Diagnostico;


@Service
public class IDxServiceImpl implements IDxService {
	
	@Autowired
	private IDxDao dxdao;
	
	

	@Override
	@Transactional(readOnly = true)
	public List<Diagnostico> findAll() {
		
		return (List<Diagnostico>) dxdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Diagnostico findById(String iddx) {
		
		return dxdao.findById(iddx).orElse(null);
	}

	@Override
	@Transactional
	public Diagnostico save(Diagnostico dx) {
		
		return dxdao.save(dx);
	}

	@Override
	@Transactional
	public void delete(String iddx) {
		
		dxdao.deleteById(iddx);
	}

}
