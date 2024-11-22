package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.SignosVitales;

public interface ISignosVitalesService {
	
	public List <SignosVitales> findAll();
	
	public SignosVitales findById(Long idsigvit);
	
	public SignosVitales save (SignosVitales sigvit);

}
