package com.js.plataformasalud.modelos.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.js.plataformasalud.modelos.entidades.CitaPaciente;
import com.js.plataformasalud.modelos.servicios.ICitaPacienteServiceImpl;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class CitaPacienteRestController {
	
	private ICitaPacienteServiceImpl citas;
	
	@GetMapping("/citapaciente")
	public List<CitaPaciente> index(){
		return citas.findAll();
	}
	
	@GetMapping("/citapaciente/{idcitpac}")
	public ResponseEntity<?> mostrar (@PathVariable Long idcitpac){
		CitaPaciente citpac = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			citpac = citas.findById(idcitpac);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if()
	}

}
