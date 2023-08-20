package com.js.plataformasalud.modelos.servicios;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.PresentacionMedInsDao;
import com.js.plataformasalud.modelos.entidades.PresentacionMedIns;

@Service

public class IPresentacionMedInsServiceImpl implements IPresentacionMedInsService{
	
	@Autowired
	private PresentacionMedInsDao pmedinsdao;

	@Override
	@Transactional(readOnly = true)
	public List<PresentacionMedIns> findAll() {

		return (List<PresentacionMedIns>)pmedinsdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public PresentacionMedIns findById(Long idpmedins) {
		
		return pmedinsdao.findById(idpmedins).orElse(null);
	}

	@Override
	@Transactional
	public PresentacionMedIns save(PresentacionMedIns prmedins) {
		
		return pmedinsdao.save(prmedins);
	}

	@Override
	@Transactional
	public void delete(Long idpmedins) {
		
		pmedinsdao.deleteById(idpmedins);
		
		
	}

}
