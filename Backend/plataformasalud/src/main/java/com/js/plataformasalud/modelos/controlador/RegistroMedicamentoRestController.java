package com.js.plataformasalud.modelos.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.js.plataformasalud.modelos.entidades.RegistroMedicamento;
import com.js.plataformasalud.modelos.servicios.IRegistroMedicamentoServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class RegistroMedicamentoRestController {
	
	
	private IRegistroMedicamentoServiceImpl regmedinsserv;
	
	@GetMapping("/registromedicamentos")
	@ResponseStatus(code = HttpStatus.OK)
	public List<RegistroMedicamento> index(){
		return regmedinsserv.findAll();
	}
	
	@GetMapping("/registromedicamentos/{idregmedins}")
	public ResponseEntity<?> mostrar(@PathVariable Long idregmedins) {
		RegistroMedicamento regmedins = null;
		Map<String, Object> response = new HashMap<>();
	
		try {
			regmedins = regmedinsserv.findById(idregmedins);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (regmedins == null) {
			response.put("mensaje", "El registro de medicamentos ID: ".concat(idregmedins.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<RegistroMedicamento>(regmedins, HttpStatus.OK);
	}
	
	@PostMapping("/registromedicamentos")
	public ResponseEntity<?> save (@Valid @RequestBody RegistroMedicamento regmedins, BindingResult result) {
		RegistroMedicamento Newregmedins = null;
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
				Newregmedins= regmedinsserv.save(regmedins);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar la aplicacion en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "El registro ha sido creado con éxito!");
		response.put("RegistroMedicamento", Newregmedins);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/registromedicamentos/{idregmedins}")
	public ResponseEntity <?> actualizar (@PathVariable Long idregmedins,@RequestBody RegistroMedicamento regmedins, BindingResult result) {
		
		RegistroMedicamento regmedinsUpdate = null;
		RegistroMedicamento regmedinsAct = regmedinsserv.findById(idregmedins);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (regmedinsAct == null) {
			response.put("mensaje", "Error: no se pudo editar, el registro de medicamento ID: "
					.concat(idregmedins.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			regmedinsAct.setDateregpmedins(regmedins.getDateregpmedins());
			regmedinsAct.setDateregpmedins(regmedins.getDateregpmedins());
			regmedinsAct.setEstregmedins_fk(regmedins.getEstregmedins_fk());
			regmedinsAct.setMedinsreg_fk(regmedins.getMedinsreg_fk());
			regmedinsAct.setRegdosismedins(regmedins.getRegdosismedins());
			regmedinsAct.setViadm_fk(regmedins.getViadm_fk());
			
			
			regmedinsUpdate = regmedinsserv.save(regmedinsAct);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar el registro de medicamentos en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "La aplicacion de medicamentos ha sido actualizado con éxito!");
		response.put("RegistroMedicamento", regmedinsUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	

}
