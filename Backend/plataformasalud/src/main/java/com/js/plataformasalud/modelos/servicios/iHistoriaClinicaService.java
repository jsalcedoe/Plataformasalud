package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.HistoriaClinica;

public interface IHistoriaClinicaService {
	
	public List<HistoriaClinica> findAll();
	
	public HistoriaClinica findById(Long idhcpac);
	
	public HistoriaClinica save (HistoriaClinica hcpac);
	
	public HistoriaClinica findByEventpac_Fk (Long idevent);
	
	public boolean existsById(Long idhcpac);
	
	

}
