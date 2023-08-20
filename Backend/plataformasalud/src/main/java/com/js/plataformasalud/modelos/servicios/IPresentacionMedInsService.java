package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.PresentacionMedIns;

public interface IPresentacionMedInsService {
	
	public List<PresentacionMedIns> findAll();
	
	public PresentacionMedIns findById(Long idpmedins);
	
	public PresentacionMedIns save(PresentacionMedIns prmedins);
	
	public void delete(Long idpmedins);

}
