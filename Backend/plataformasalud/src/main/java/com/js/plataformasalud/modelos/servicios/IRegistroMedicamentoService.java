package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import com.js.plataformasalud.modelos.entidades.RegistroMedicamento;

public interface IRegistroMedicamentoService {
	
	public List<RegistroMedicamento> findAll();
	
	public RegistroMedicamento findById (Long idregmedins);
	
	public RegistroMedicamento save (RegistroMedicamento regmedins);
	
	

}
