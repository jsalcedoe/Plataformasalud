package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.Cargo;

public interface ICargoServices {
	
	public List<Cargo> findAll();
	
	public Cargo findById(Long idcarguser);
	
	public Cargo save(Cargo cargo);
	
	public void delete (Long idcarguser);

}
