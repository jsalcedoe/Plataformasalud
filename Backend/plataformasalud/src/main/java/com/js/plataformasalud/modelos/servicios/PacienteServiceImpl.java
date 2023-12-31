package com.js.plataformasalud.modelos.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.js.plataformasalud.modelos.dao.ipacientedao;
import com.js.plataformasalud.modelos.entidades.Paciente;

@Service
public class PacienteServiceImpl implements ipacienteservice {// en esta clase se implementa la interfaz de servicio creada
	
	//realizamos la inyeccion de dependencias de la clase dao
	@Autowired
	private ipacientedao pacientedao;// con este atributo se accede a los pacientes
	
	@Override
	@Transactional(readOnly = true)
	public List<Paciente> findAll() {
		
		return (List<Paciente>)pacientedao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Paciente findById(Long numdocpac) {
		return pacientedao.findById(numdocpac).orElse(null);
	}
	
	@Override
	@Transactional
	public Paciente save(Paciente paciente) {
		return pacientedao.save(paciente);
	}
	
	@Override
	@Transactional
	public void delete(Long numdocpac) {
		pacientedao.deleteById(numdocpac);
		
	}

	
	
}
