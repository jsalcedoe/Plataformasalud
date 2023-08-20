package com.js.plataformasalud.modelos.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.js.plataformasalud.modelos.entidades.Paciente;
import com.js.plataformasalud.modelos.servicios.PacienteServiceImpl;
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class PacienteRestController {
	//2. ahora debemos obtener los pacientes del servicio, para esto hacemos inyeccion de dependencia
	@Autowired
	private PacienteServiceImpl pacienteservice;
	
	//3. ahora mapeamos la ruta para generar el endpoint del metodo
	@GetMapping("/pacientes")
	public List<Paciente> index(){//1. creamos el metodo index para listar los pacientes
		return pacienteservice.findAll();
		
	}
	
	@GetMapping("/pacientes/{numdocpac}")
	@ResponseStatus(HttpStatus.OK)
	public Paciente mostrar(@PathVariable Long numdocpac) {
		return pacienteservice.findById(numdocpac);
	}
	
	@PostMapping("/pacientes")
	@ResponseStatus(HttpStatus.CREATED)
	public Paciente crear (@RequestBody Paciente paciente) {
		return pacienteservice.save(paciente);
	}
	
	@PutMapping("/pacientes/{numdocpac}")
	public Paciente update (@RequestBody Paciente paciente, @PathVariable Long numdocpac) {
		Paciente pacienteActual = pacienteservice.findById(numdocpac);
		
		pacienteActual.setTipodoc(paciente.getTipodoc());
		pacienteActual.setPrimernompac(paciente.getPrimernompac());
		pacienteActual.setSegundonompac(paciente.getSegundonompac());
		pacienteActual.setPrimerapepac(paciente.getSegundoapepac());
		pacienteActual.setSegundoapepac(paciente.getSegundoapepac());
		pacienteActual.setSexopac(paciente.getSexopac());
		pacienteActual.setFechanacpac(paciente.getFechanacpac());
		pacienteActual.setEdadpac(paciente.getEdadpac());
		pacienteActual.setEstadocivilpac(paciente.getEstadocivilpac());
		pacienteActual.setDireccionpac(paciente.getDireccionpac());
		pacienteActual.setEmailpac(paciente.getEmailpac());
		pacienteActual.setContactopac(paciente.getContactopac());
		pacienteActual.setAcudientepac(paciente.getAcudientepac());
		pacienteActual.setContactoacudientepac(paciente.getContactoacudientepac());
		pacienteActual.setCiudad(paciente.getCiudad());
		pacienteActual.setEstado(paciente.getEstado());
		
		
		return pacienteservice.save(pacienteActual);
	}
	
	
}
