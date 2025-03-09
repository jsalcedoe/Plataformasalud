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
import com.js.plataformasalud.modelos.entidades.ConsentimientoPaciente;
import com.js.plataformasalud.modelos.servicios.IConsentimientoPacienteServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class ConsentimientoPacienteRestController {
	
	private IConsentimientoPacienteServiceImpl coninfpacserv;
	
	@GetMapping("/consentimientopaciente")
	@ResponseStatus(code = HttpStatus.OK)
	public List<ConsentimientoPaciente> index(){
		return coninfpacserv.findAll();
	}
	
	@GetMapping("/consentimientopaciente/{idconsinfpac}")
	public ResponseEntity<?> mostrar(@PathVariable Long idconsinfpac) {
		ConsentimientoPaciente consinfpac = null;
		Map<String, Object> response = new HashMap<>();
	
		try {
			consinfpac = coninfpacserv.findById(idconsinfpac);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (consinfpac == null) {
			response.put("mensaje", "El consentimiento ID: ".concat(idconsinfpac.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ConsentimientoPaciente>(consinfpac, HttpStatus.OK);
	}
	
	@GetMapping("/consentimientopaciente/listar/{idevent}")
	public ResponseEntity<?> ListarConsentimientos (@PathVariable Long idevent){
		List<ConsentimientoPaciente> consinfpac = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
				consinfpac = coninfpacserv.findByEventconsinfpac_fk(idevent);
			} catch(DataAccessException e) {
		response.put("mensaje", "Error al realizar la consulta en la base de datos");
		response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	if(consinfpac == null) {
		response.put("mensaje", "El evento ID: ".concat(idevent.toString().concat(" no tiene consentimientos informados en la base de datos!")));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	}
	
	return new ResponseEntity<>(consinfpac, HttpStatus.OK);
	
}
	
	@PostMapping("/consentimientopaciente")
	public ResponseEntity<?> save (@Valid @RequestBody ConsentimientoPaciente consinfpac, BindingResult result) {
		ConsentimientoPaciente Newconsinfpac = null;
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
				Newconsinfpac= coninfpacserv.save(consinfpac);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al registrar el consentimiento informado del paciente en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		response.put("mensaje", "El consentimiento informado del paciente ha sido creado con éxito!");
		response.put("Agenda", Newconsinfpac);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/consentimientopaciente/{idconsinfpac}")
	public ResponseEntity <?> actualizar (@PathVariable Long idconsinfpac,@RequestBody ConsentimientoPaciente consinfpac, BindingResult result) {
		
		ConsentimientoPaciente consinfpacUpdate = null;
		ConsentimientoPaciente consinfpacAct = coninfpacserv.findById(idconsinfpac);
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (consinfpacAct == null) {
			response.put("mensaje", "Error: no se pudo editar, el consentimiento ID: "
					.concat(idconsinfpac.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			consinfpacAct.setConspxpac_fk(consinfpac.getConspxpac_fk());
			consinfpacAct.setDatecreatconsinfpac(consinfpac.getDatecreatconsinfpac());
			consinfpacAct.setDateeditconinfpac(consinfpac.getDateeditconinfpac());
			consinfpacAct.setMedpxpac_fk(consinfpac.getMedpxpac_fk());
			consinfpacAct.setStatuconsinfpac_fk(consinfpac.getStatuconsinfpac_fk());
						
			consinfpacUpdate = coninfpacserv.save(consinfpacAct);
			
			}catch(DataAccessException e) {
				response.put("mensaje", "Error al actualizar el consentimiento informado del paciente en la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		response.put("mensaje", "El consentimiento informado del paciente ha sido actualizado con éxito!");
		response.put("Agenda", consinfpacUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
